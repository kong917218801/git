package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.BeanUtils;
import com.cpsdb.base.common.CPSDateUtils;
import com.cpsdb.base.common.ImageUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.exception.NoPermissionException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.base.streaming.Streamable;
import com.cpsdb.baseservapi.api.AreaApi;
import com.cpsdb.baseservapi.dto.ApiArea;
import com.cpsdb.declareapi.dto.ApiDeclareInfo;
import com.cpsdb.declareapi.dto.ApiDeclarerInfo;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.params.ApiQueryParams;
import com.cpsdb.declareapi.params.ApiScoreParams;
import com.cpsdb.declareapi.utils.DeclareTokenUtils;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.DeclarerMapper;
import com.cpsdb.declareserv.dao.OperationLogMapper;
import com.cpsdb.declareserv.dto.*;
import com.cpsdb.declareserv.entity.*;
import com.cpsdb.declareserv.params.declarer.*;
import com.cpsdb.declareserv.service.*;
import com.cpsdb.declareserv.utils.*;
import com.cpsdb.examinationapi.api.DeclareCheckApi;
import com.cpsdb.payapi.utils.PayMethod;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import com.cpsdb.zimgapi.api.ZimgClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarerManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AreaApi areaApi;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    @Autowired
    private DeclareCheckApi declareCheckApi;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private ZimgClient zimgClient;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private JuHeApi juHeApi;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private DeclarerCodeDetailService declarerCodeDetailService;

    @Autowired
    private DeclarerMapper declarerMapper;

    @Autowired
    private DeclareToEnterpriseService declareToEnterpriseService;

    @Autowired
    private DeclareEnterpriseService declareEnterpriseService;


    public JDeclarerList getDetailByZhs(Long id) {
        Declarer declarer = declarerService.getById(id);
        AssertUtils.notNull(declarer, new CustomException("获取申报官信息失败"));

        return BeanUtils.copyObject(declarer, new JDeclarerList()
                .setId(declarer.getId())
                .setPortrait(declarer.getPortrait())
                .setName(declarer.getName())
                .setSn(SnUtils.getDeclarerSn(declarer.getId().toString()))
                .setIdNumber(declarer.getIdNumber())
                .setOrganizName(declarer.getFkOrganizId() == null ? null : organizService.getById(declarer.getFkOrganizId()).getName())
                .setScore(declarer.getScore().toString())
                .setCellphone(declarer.getCellphone())
                .setIdBackUrl(declarer.getIdBackUrl())
                .setIdFrontUrl(declarer.getIdFrontUrl())
                .setOrganizPhone(declarer.getFkOrganizId() == null ? null : organizService.getById(declarer.getFkOrganizId()).getCellphone())
                .setState(DeclarerUtils.DeclarerState.getState(declarer.getState()))
        );
    }

    public List<MDeclarer> query(JDeclarerQueryParams params, DatagridPager pager) {
        List<Declarer> declarerList = declarerService.query(params, pager);

        return declarerList.stream().map(s -> new MDeclarer()
                .setId(s.getId())
                .setSn(SnUtils.getDeclarerSn(s.getId().toString()))
                .setName(s.getName())
                .setScore(s.getScore())
                .setState(s.getState())
                .setOrganizName(s.getOrganizName())
                .setServiceName(s.getServiceName())
                .setRegisterTime(s.getCreateTime().getTime())
                .setCreateTime(s.getCreateTime())
                .setCellphone(s.getCellphone())
                .setIdNumber(s.getIdNumber()))
                .collect(Collectors.toList());
    }

    public Long count(JDeclarerQueryParams params) {
        return declarerService.count(params);
    }

    //申报官APP账户中心
    public JAppLogin account(User user, Declarer declarer, Organiz organiz) throws Exception {

        //获取推荐人工号
        Recommend recommend = recommendService.getByTargetUserId(user.getId());
        return new JAppLogin()
                .setState(declarer.getState())
                .setAmount(DeclareConfigUtils.getBigDecimal("pay.declarer.amount"))
                .setSn(SnUtils.getDeclarerSn(declarer.getId().toString()))
                .setOrganiz(organiz.getCompanyName())
                .setRecommendId(user.getUsername())
                .setRecommendName(recommend == null ? null : userService.getById(recommend.getRecommendId()).getUsername())
                .setType(user.getType())
                .setExamination(declarer.getState().equals(DeclarerUtils.DeclarerState.passed.name()) ? declareCheckApi.check(user.getId(), declarer.getScore().intValue(), declarer.getCreateTime().getTime()) : false)
                .setName(declarer.getName())
                .setScore(declarer.getScore().toString())
                .setReason(declarer.getReason())
                .setCode(DeclarerUtils.declarerQrcode(declarer.getId()))
                .setPortrait(declarer.getPortrait())
                .setToken(DeclareTokenUtils.encrypt(user.getType(), user.getId(), user.getObjectId(), user.getUsername()));
    }

    //申报官认证信息
    public void authentication(Long declarerId, JDeclarerAuthenParams params, MultipartFile portraitFile, MultipartFile signFile, DeclarerSignParams signParams) throws Exception {
        Declarer declarer = declarerService.getById(declarerId);
        AssertUtils.notNull(declarer, new CustomException("该申报官不存在！"));
        signParams.setAddress(signParams.getAddress());
        //生成承诺公函照片、尽职调查表
        Map<String, String> maps = this.sign(portraitFile, signFile, signParams, declarer);
        params.setSurveyImageUrl(maps.get("jzdcbUrl"));
        params.setLetterImageUrl(maps.get("cnghUrl"));
        declarerService.updateAuthen(declarer, params, signParams);

        Order order = orderService.getByDeclarerId(declarerId, DeclarerUtils.PayState.success.name());
        if(order.getPayMethod().equals(PayMethod.offline.name())){
            declarerService.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.submit, DeclareHolder.getUsername(), "认证信息已提交！");
        }else {
            declarerService.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.submit, DeclareHolder.getUsername(), "认证信息已提交！");
            declarerService.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.confirm, DeclareHolder.getUsername(), "系统财务审核已通过！");
        }
    }

    private Map<String, String> sign(MultipartFile portraitFile, MultipartFile signFile, DeclarerSignParams signParams, Declarer declarer) throws Exception {
        // 设置插入文字字体
        Font f = new Font("Serif", 1, 24);
        Image sign = null;
        if (signFile != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            IOUtils.copy(signFile.getInputStream(), outputStream);
            byte[] byteArray = outputStream.toByteArray();
            sign = ImageIO.read(new ByteArrayInputStream(byteArray));
        }
        // 承诺公函
        BufferedImage cngh = ImageIO.read(this.getClass().getResourceAsStream("/templates/sbgcngh.png"));
        // 承诺公函插入签名
        cngh = ImageUtils.insertImage(cngh, sign, 1190, 1860);
        // 承诺公函插入时间
        cngh = ImageUtils.insertTitle(cngh, CPSDateUtils.toCNString(new Date()), Color.black, f, 1041, 2035);
        // 上传承诺公函
        ByteArrayOutputStream cnghos = new ByteArrayOutputStream();
        ImageIO.write(cngh, ImageUtils.FORMAT_NAME_PNG, cnghos);
        String cnghUrl = zimgClient.upload(new ByteArrayInputStream(cnghos.toByteArray()), ImageUtils.FORMAT_NAME_PNG).getInfo().getMd5();

        // 尽职调查表
        BufferedImage jzdcb = ImageIO.read(this.getClass().getResourceAsStream("/templates/sbgjzdcb.png"));
        // 插入签名
        jzdcb = ImageUtils.insertImage(jzdcb, sign, 1100, 1646);
        jzdcb = ImageUtils.insertTitle(jzdcb, signParams.getName(), Color.black, f, 430, 350);
        jzdcb = ImageUtils.insertTitle(jzdcb, signParams.getSex(), Color.black, f, 800, 350);
        jzdcb = ImageUtils.insertTitle(jzdcb, signParams.getVolk(), Color.black, f, 430, 430);
        jzdcb = ImageUtils.insertTitle(jzdcb, declarer.getCellphone(), Color.black, f, 800, 430);
        jzdcb = ImageUtils.insertTitle(jzdcb, declarer.getIdNumber(), Color.black, f, 310, 520);
        jzdcb = ImageUtils.insertTitle(jzdcb, signParams.getAddress(), Color.black, f, 310, 610);
        jzdcb = ImageUtils.insertTitle(jzdcb, signParams.getEducation(), Color.black, f, 310, 700);
        jzdcb = ImageUtils.insertTitle(jzdcb, signParams.getUniversity(), Color.black, f, 720, 700);

        // 插入寸照
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(portraitFile.getInputStream(), outputStream);
        byte[] byteArray = outputStream.toByteArray();
        BufferedImage portrait = ImageIO.read(new ByteArrayInputStream(byteArray));
        //  topleft : 1136 302  rightbuttom:  1346 553
        portrait = ImageUtils.compressImage(portrait, 200, 251);
        jzdcb = ImageUtils.insertImage(jzdcb, portrait, 1145, 302);

        // 插入yes no
        BufferedImage yes = ImageIO.read(this.getClass().getResourceAsStream("/templates/yes.png"));
        jzdcb = ImageUtils.insertImage(jzdcb, yes, signParams.isJudicialDecision() ? 613 : 721, 995);
        jzdcb = ImageUtils.insertImage(jzdcb, yes, signParams.isJudicialExecution() ? 1122 : 1231, 995);

        jzdcb = ImageUtils.insertImage(jzdcb, yes, signParams.isAdministrativePenalty() ? 613 : 721, 1072);
        jzdcb = ImageUtils.insertImage(jzdcb, yes, signParams.isArrearsOfTaxes() ? 1122 : 1231, 1072);

        jzdcb = ImageUtils.insertImage(jzdcb, yes, signParams.isHasHonor() ? 613 : 721, 1150);
        jzdcb = ImageUtils.insertImage(jzdcb, yes, signParams.isHasAward() ? 1122 : 1231, 1150);

        // 处理简介
        jzdcb = insertLongContent(signParams.getProfile(), jzdcb, f, 301, 756);
        // 处理荣誉
        jzdcb = insertLongContent(signParams.getHonorDetail(), jzdcb, f, 301, 1210);
        // 处理申报理由
        jzdcb = insertLongContent(signParams.getReason(), jzdcb, f, 301, 1455);

        // 上传尽职调查表
        ByteArrayOutputStream jzdcbos = new ByteArrayOutputStream();
        ImageIO.write(jzdcb, ImageUtils.FORMAT_NAME_PNG, jzdcbos);
        String jzdcbUrl = zimgClient.upload(new ByteArrayInputStream(jzdcbos.toByteArray()), ImageUtils.FORMAT_NAME_PNG).getInfo().getMd5();

        Map<String, String> r = new HashMap<>();
        r.put("cnghUrl", cnghUrl);
        r.put("jzdcbUrl", jzdcbUrl);
        return r;
    }

    private static BufferedImage insertLongContent(String content, BufferedImage image, Font f, int startX, int startY) {
        if (StringUtils.isBlank(content)) {
            return image;
        }

        // 一行41个字
//        for (int i = 0; i < content.length(); i += 41) {
//            String sub = content.substring(i, i + 41);
//            image = ImageUtils.insertTitle(image, sub, Color.black, f, startX, startY + 30);
//        }
        if (content.length() < 41) {
            image = ImageUtils.insertTitle(image, content, Color.black, f, startX, startY + 30);
        }

        return image;
    }

    public JDeclarerDetail declarerDetails(Long declarerId) {
        Declarer declarer = declarerService.getById(declarerId);

        if (declarer.getState().equals(DeclarerUtils.DeclarerState.waitPay.name())) {
            return null;
        }
//        获取推荐人工号
        User user = userService.getByObjectId(declarerId, UserUtils.UserType.declarer);
        Recommend recommend = recommendService.getByTargetUserId(user.getId());

        String areaCode = declarer.getAreaCode();
        ApiArea area = null;
        if (areaCode != null) {
            area = areaApi.getByCode(areaCode);
        }
        Order order = orderService.getByDeclarerId(declarerId, DeclarerUtils.PayState.success.name());
        Invoice invoice = invoiceService.getByOrderId(order == null ? null : order.getId());
        Organiz organiz = organizService.getById(declarer.getFkOrganizId());
        List<OperationLog> logs = operationLogMapper.getByIdType(declarer.getId(), OperationLogUtils.Type.declarer);
        return BeanUtils.copyObject(declarer, new JDeclarerDetail())
                .setPortrait(declarer.getPortrait())
                .setArea(area == null ? null : area.getArea())
                .setOrder(order)
                .setInvoice(invoice)
                .setName(declarer.getName())
                .setCellphone(declarer.getCellphone())
                .setIdNumber(declarer.getIdNumber())
                .setIdBackUrl(declarer.getIdBackUrl())
                .setIdFrontUrl(declarer.getIdFrontUrl())
                .setScore(declarer.getScore())
                .setPaymentUrl(declarer.getPaymentUrl())
                .setLetterImageUrl(declarer.getLetterImageUrl())
                .setDeclareGuideImageUrl(declarer.getDeclareGuideImageUrl())
                .setState(declarer.getState())
                .setOrganizName(organiz.getName())
                .setSurveyImageUrl(declarer.getSurveyImageUrl())
                .setIspass(declarer.getIspass())
                .setReason(declarer.getReason())
                .setCode(DeclarerUtils.declarerQrcode(declarer.getId()))
                .setAddress(declarer.getAddress() == null ? "" : declarer.getAddress())
                .setLogs(logs)
                .setSn(SnUtils.getDeclarerSn(declarer.getId().toString()))
                .setOrganiz(organiz.getCompanyName())
                .setSex(declarer.getSex())
                .setNation(declarer.getNation())
                .setEducation(declarer.getEducation())
                .setGraduate(declarer.getGraduate())
                .setPayTime(order == null ? null : order.getPayTime())
                .setRecommend(recommend == null ? null : userService.getById(recommend.getRecommendId()).getUsername())
                ;
    }

    public JAppLogin appRegist(JAppRegistParams params) throws Exception {
        juHeApi.vilidaty(ModeType.ADDDECLARE, params.getCellphone(), params.getCode());

        Long organizId = DeclareConfigUtils.getLong("base.organiz.id");
        AssertUtils.notNull(organizId, new CustomException("系统异常，请联系管理员！"));

        //验证是否已经注册、推荐人是否存在
        AssertUtils.isNull(declarerService.getByIdNumber(params.getIdnumber()), new CustomException("该身份证已被注册！"));

        Organiz organiz = organizService.getById(organizId);
        return declarerService.insertInfo(organiz, params.getName(), params.getCellphone(), params.getIdnumber(), params.getRecommend(), params.getPassword());
    }

    public List<JDeclarer> getDeclarerList(JDeclarerQueryParams params, Long objectId, UserUtils.UserType userType, DatagridPager pager) {
        List<Declarer> declarerList = new ArrayList<>();
        if (userType.equals(UserUtils.UserType.organiz)) {
            declarerList = declarerService.getByOrganizId(params, objectId, pager);
        } else if (userType.equals(UserUtils.UserType.service)) {
            declarerList = declarerService.getByServiceId(params, objectId, pager);
        }

//        Set<Long> organizIds = declarerList.stream().map(Declarer::getFkOrganizId).collect(Collectors.toSet());
//        List<Organiz> organizList = organizService.getByIds(organizIds);
//        Map<Long,String> organizsMap = Streamable.valueOf(organizList).uniqueGroupby(Organiz::getId,Organiz::getName).collect();

        return declarerList.stream().map(s -> new JDeclarer()
                .setId(s.getId())
                .setSn(SnUtils.getDeclarerSn(s.getId().toString()))
                .setCellphone(s.getCellphone())
                .setIdNumber(s.getIdNumber())
                .setName(s.getName())
                .setState(s.getState())
                .setAddress(s.getAddress())
                .setAreaCode(s.getAreaCode())
                .setCreateTime(s.getCreateTime())
                .setDeclareGuideImageUrl(s.getDeclareGuideImageUrl())
                .setEducation(s.getEducation())
                .setGraduate(s.getGraduate())
                .setSurveyImageUrl(s.getSurveyImageUrl())
                .setSex(s.getSex())
                .setScore(s.getScore())
                .setReason(s.getReason())
                .setPortrait(s.getPortrait())
                .setNation(s.getNation())
                .setLetterImageUrl(s.getLetterImageUrl())
                .setIspass(s.getIspass())
                .setIdFrontUrl(s.getIdFrontUrl())
                .setIdBackUrl(s.getIdBackUrl())
                .setOrganizName(organizService.getById(s.getFkOrganizId()).getName())
                .setServiceName(s.getServiceName())

        ).collect(Collectors.toList());
    }

    public Long getDeclarerCount(JDeclarerQueryParams params, Long objectId, UserUtils.UserType userType) {
        if (userType.equals(UserUtils.UserType.organiz)) {
            return declarerService.getByOrganizCount(params, objectId);
        } else if (userType.equals(UserUtils.UserType.service)) {
            return declarerService.getByServiceCount(params, objectId);
        }
        return null;
    }


    /**
     * 服务中申请删除申报官
     */
    public void organizDeleteDeclarer(Long declarerId, Long organizId, String username, String reason) {
        Declarer declarer = declarerService.getById(declarerId);
        if (!declarer.getFkOrganizId().equals(organizId)) {
            throw new CustomException("您不能执行该操作！");
        }
        declarerService.processEvent(declarerId, DeclarerUtils.DeclarerEvent.delete, username, reason);
    }

    public void platformDeleteDeclarer(Long declarerId, String operator, String reason) {
        Declarer declarer = declarerService.getById(declarerId);
        AssertUtils.notNull(declarer, new CustomException("该申报官不存在！"));

        AssertUtils.isTrue(declarer.getState().equals(DeclarerUtils.DeclarerState.delete.name()), new CustomException("您不能删除该申报官！"));
        declarerService.deleteDeclarer(declarer, operator, reason);
    }

    public void confirm(Long id, Boolean state, String operator, String reason) {
        if (state) {
            declarerService.processEvent(id, DeclarerUtils.DeclarerEvent.confirm, operator, reason);
        } else {
            AssertUtils.notNull(reason, new CustomException("请输入不通过原因！"));
            declarerService.processEvent(id, DeclarerUtils.DeclarerEvent.reject, operator, reason);
        }
    }

    public List<JCodeDetails> getCodeDetails(Long declarerId) {
        List<DeclarerCodeDetail> declarerCodeDetails = declarerCodeDetailService.getDetailsByDeclarerId(declarerId);
        return declarerCodeDetails.stream()
                .map(s -> BeanUtils.copyObject(s, new JCodeDetails()
                        .setAddress(s.getAddress())
                        .setId(s.getId())
                        .setCreateTime(s.getCreateTime().getTime()))
                ).collect(Collectors.toList());
    }

    public List<JDeclarerPublicList> getPublicDeclarerList(JDeclarerPublicParams params, DatagridPager pager) {
        String code = areaApi.substringCode(params.getAreaCode());
        List<Declarer> declarerList = declarerService.getPublicDeclarerList(params.setAreaCode(code), pager);
        if(declarerList.size()<1){
            return null;
        }
        //获取申报服务处地区
        Set<String> areaCosSet = declarerList.stream().map(Declarer::getOrganizAreaCode).collect(Collectors.toSet());
        List<ApiArea> apiAreas = areaApi.getByCode(areaCosSet);
        Map<String, String> areaMap = Streamable.valueOf(apiAreas).uniqueGroupby(ApiArea::getAreaCode, ApiArea::getArea).collect();

        //获取申报服务处电话号码
        Set<Long> organizSet = declarerList.stream().map(Declarer::getFkOrganizId).collect(Collectors.toSet());
        List<Organiz> organizList = organizService.getByIds(organizSet);
        Map<Long, String> organizMap = Streamable.valueOf(organizList).uniqueGroupby(Organiz::getId, Organiz::getCellphone).collect();

        return declarerList.stream().map(s -> new JDeclarerPublicList()
                .setId(s.getId())
                .setSn(SnUtils.getDeclarerSn(s.getId().toString()))
                .setName(s.getName())
                .setOrganizName(s.getOrganizName())
                .setCellphone(s.getCellphone())
                .setDeclarerAddress(areaMap.get(s.getOrganizAreaCode()) + s.getOrganizAddress())
                .setOrganizCellphone(organizMap.get(s.getFkOrganizId()))
        ).collect(Collectors.toList());
    }

    public Long getPublicDeclarerCount(JDeclarerPublicParams params) {
        return declarerService.getPublicDeclarerCount(params);
    }

    public Boolean declarerScore(ApiScoreParams params) {

        try {
            User user = userService.getById(params.getUserId());
            Declarer declarer = declarerService.getById(user.getObjectId());
            AssertUtils.notNull(declarer, new CustomException("该申报官不存在！"));
            declarerService.updateDeclarerScore(declarer, params.getScore(), params.getIspass());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer getDeclarer(ApiQueryParams params) {
        User user = userService.getById(params.getUserId());
        return declarerMapper.getDeclarer(params.setDeclarerId(user.getObjectId()));
    }

    public JDeclarerDto getDeclarer(Long declarerId) {
        Declarer s = declarerService.getById(declarerId);
        User user = userService.getByObjectId(declarerId, UserUtils.UserType.declarer);
        //获取推荐人工号
        Recommend recommend = recommendService.getByTargetUserId(user.getId());
        return new JDeclarerDto()
                .setId(s.getId())
                .setSn(SnUtils.getDeclarerSn(declarerId.toString()))
                .setCellphone(s.getCellphone())
                .setIdNumber(s.getIdNumber())
                .setName(s.getName())
                .setRecommend(recommend == null ? null : userService.getById(recommend.getRecommendId()).getUsername())
                .setState(s.getState())
                .setAddress(s.getAddress())
                .setAreaCode(s.getAreaCode() == null ? null : areaApi.getByCode(s.getAreaCode()).getArea())
                .setCreateTime(s.getCreateTime())
                .setDeclareGuideImageUrl(s.getDeclareGuideImageUrl())
                .setEducation(s.getEducation())
                .setGraduate(s.getGraduate())
                .setSurveyImageUrl(s.getSurveyImageUrl())
                .setSex(s.getSex())
                .setScore(s.getScore())
                .setReason(s.getReason())
                .setPortrait(s.getPortrait())
                .setNation(s.getNation())
                .setLetterImageUrl(s.getLetterImageUrl())
                .setIdFrontUrl(s.getIdFrontUrl())
                .setIdBackUrl(s.getIdBackUrl())
                .setOrganizName(organizService.getById(s.getFkOrganizId()).getName())
                .setProvenceName(provenceCenterService.getById(s.getFkProvenceCenterId()).getCompanyName());
    }

    public ApiDeclarerInfo getDeclarers(Long userId) {
        User user = userService.getById(userId);
        Declarer declarer = declarerService.getById(user.getObjectId());
        return new ApiDeclarerInfo()
                .setId(declarer.getId())
                .setCellphone(declarer.getCellphone())
                .setName(declarer.getName())
                .setIdNumber(declarer.getIdNumber());
    }

    public ApiDeclareInfo getByEnterpriserId(Long enterpriserId) {
        DeclareToEnterprise declareToEnterprise = declareToEnterpriseService.getByEnterpriseId(enterpriserId);
        DeclareEnterprise enterprise = declareEnterpriseService.getById(declareToEnterprise.getFkDeclareEnterpriseId());
        User user = userService.getById(enterprise.getFkUserId());
        Declarer declarer = new Declarer();
        Organiz organiz = new Organiz();
        if (user.getObjectId() != null) {
            declarer = declarerService.getById(user.getObjectId());
        } else {
            organiz = organizService.getById(user.getObjectId());
        }
        return new ApiDeclareInfo().setName(user.getObjectId() == null ? organiz.getName() : declarer.getName()).setPhone(user.getObjectId() == null ? organiz.getCellphone() : declarer.getCellphone());
    }

    /**
     * 将申报官委派到指定的服务机构
     *
     * @param declarerId
     * @param organizId
     */
    public void designate(Long declarerId, Long organizId) {
        Organiz organiz = organizService.getById(organizId);
        Declarer declarer = declarerService.getById(declarerId);
        AssertUtils.notNull(organiz);
        AssertUtils.notNull(declarer);

        AssertUtils.isTrue(declarer.getState().equals(DeclarerUtils.DeclarerState.passed.name()), new CustomException("该申报官还未审核通过！"));
        AssertUtils.isTrue(organiz.getState().equals(OrganizUtils.OrganizState.passed.name()), new CustomException("该申报服务处还未审核通过！"));
        // 修改申报官的服务处归属
        this.declarerService.updateOrganiz(declarerId, organizId);
    }

    public void createByOrganiz(JOrganizRegistParams params) {
        juHeApi.vilidaty(ModeType.ADDDECLARE, params.getCellphone(), params.getCode());

        UserUtils.UserType userType = DeclareHolder.getUserType();
        Long organizId = null;
        switch (userType) {
            case organiz:
                // 如果是服务处添加申报官，则将申报官绑定到该服务处下
                organizId = DeclareHolder.getObjectId();
                break;
            default:
                throw new NoPermissionException();
        }
        Organiz organiz = organizService.getById(organizId);

        Declarer declarer = declarerService.getByRegist(organizId, null, DeclarerUtils.RegistMethod.other.name());
        AssertUtils.isNull(declarer, new CustomException("您只能创建一个申报官！"));

        AssertUtils.isTrue(organiz.getState().equals(OrganizUtils.OrganizState.passed.name()), new CustomException("您还未通过审核！"));

        //验证是否已经注册
        AssertUtils.isNull(declarerService.getByIdNumber(params.getIdnumber()), new CustomException("该身份证已被注册！"));

        declarerService.createByOrganiz(organiz, organiz.getFkProvenceCenterId(), params, organiz.getCellphone());
    }

    public void createByService(JOrganizRegistParams params) {

        juHeApi.vilidaty(ModeType.ADDDECLARE, params.getCellphone(), params.getCode());

        UserUtils.UserType userType = DeclareHolder.getUserType();
        Long organizId = null;
        switch (userType) {
            case service:
                // 如果是省级服务中心添加申报官，则将申报官绑定到中心默认的机构下
                organizId = DeclareConfigUtils.getLong("base.organiz.id");
                AssertUtils.notNull(organizId, new CustomException("系统异常，请联系管理员！"));
                break;
            default:
                throw new NoPermissionException();
        }
        //验证是否已经注册
        AssertUtils.isNull(declarerService.getByIdNumber(params.getIdnumber()), new CustomException("该身份证已被注册！"));
        //验证是否已经注册
        AssertUtils.isNull(declarerService.getByPhone(params.getCellphone()), new CustomException("该手机号码已被注册！"));
        Long serviceId = DeclareHolder.getObjectId();
        ProvenceCenter center = provenceCenterService.getById(serviceId);
        Declarer declarer = declarerService.getByRegist(null, serviceId, DeclarerUtils.RegistMethod.other.name());
        AssertUtils.isNull(declarer, new CustomException("您只能创建一个申报官！"));

        Organiz organiz = organizService.getById(organizId);

        declarerService.createByOrganiz(organiz, center.getId(), params, center.getCellphone());
    }

}
