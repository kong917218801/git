package com.cpsdb.declareserv.service;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.common.CPSStringUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.Pageable;
import com.cpsdb.declareserv.dao.AuthofficerMapper;
import com.cpsdb.declareserv.dto.JAuthofficer;
import com.cpsdb.declareserv.entity.Authofficer;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerParams;
import com.cpsdb.declareserv.params.authofficer.JAuthofficerQueryParams;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.redis.annotation.Lock;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 孔清
 * @ClassName AuthofficerService
 * @Description
 * @date 2018-01-05 14:47:35
 */
@Service
public class AuthofficerService {

    @Autowired
    private AuthofficerMapper authofficerMapper;

    @Autowired
    private JuHeApi juHeApi;

    public void insert(Authofficer authofficer) {
        authofficerMapper.insert(authofficer);
    }

    public void update(Authofficer authofficer) {
        authofficerMapper.update(authofficer);
    }

    public Authofficer getById(Long id) {
        return authofficerMapper.getById(id);
    }

    public Authofficer getByCellPhone(String cellPhone) {
        return authofficerMapper.getByCellPhone(cellPhone);
    }

    @Lock(key = "create_authofficer",argsIndex = 0)
    public void insert(JAuthofficerParams params, String validateCode) {
        juHeApi.vilidaty(ModeType.AUTHOFFICER_REGISTER, params.getCellphone(), validateCode);

        Authofficer authofficer = this.getByCellPhone(params.getCellphone());
        AssertUtils.isNull(authofficer, new CustomException("电话号码已经存在！"));

        this.validate(params);

        this.insert(new Authofficer()
                .setAvator(params.getAvator())
                .setAgreement(params.getAgreement())
                .setIdCard(params.getIdCard())
                .setCardY(params.getIdFrontUrl())
                .setCardN(params.getIdBackUrl())
                .setName(params.getName())
                .setOrganiz(params.getOrganiz())
                .setOPhone(params.getOrganizTel())
                .setTel(params.getCellphone())
                .setSore(params.getScore())
                .setIspass(false)
                .setInsertTime(System.currentTimeMillis() / 1000L)
                .setUpdateTime(System.currentTimeMillis() / 1000L)
        );
    }

    private void validate(JAuthofficerParams params) {
        AssertUtils.isTrue(
                StringUtils.isNotBlank(params.getName())
                        && StringUtils.isNotBlank(params.getAgreement())
                        && StringUtils.isNotBlank(params.getAvator())
                        && StringUtils.isNotBlank(params.getCellphone())
                        && StringUtils.isNotBlank(params.getIdBackUrl())
                        && StringUtils.isNotBlank(params.getIdCard())
                        && StringUtils.isNotBlank(params.getIdFrontUrl())
                        && StringUtils.isNotBlank(params.getOrganiz())
                        && StringUtils.isNotBlank(params.getOrganizTel())
                        && StringUtils.isNotBlank(params.getScore()),
                new CustomException("参数有误！")
        );

        Integer score = CPSStringUtils.parseInt(params.getScore());
        if (score == null || score < 0 || score > 100) {
            throw new CustomException("分数录入不合法");
        }

    }

    public void updateState(Long id, boolean state) {
        Authofficer authofficer = this.getById(id);
        if (authofficer.getIspass() != state) {
            this.authofficerMapper.updateState(id, state);
            if (state) {
                //审核通过
                juHeApi.sendSms(authofficer.getTel(), ModeType.AUTHOFFICER_PASS, "", authofficer.getName());
            } else {
                //审核不通过
                juHeApi.sendSms(authofficer.getTel(), ModeType.AUTHOFFICER_UNPASS, "", authofficer.getName());
            }
        }
    }

    public List<JAuthofficer> query(JAuthofficerQueryParams params, Pageable pager) {
        return this.authofficerMapper.query(params, pager)
                .stream()
                .map(s -> new JAuthofficer()
                        .setId(s.getId().longValue())
                        .setIdCard(s.getIdCard())
                        .setIdFrontUrl(s.getCardY())
                        .setIdBackUrl(s.getCardN())
                        .setAvator(s.getAvator())
                        .setName(s.getName())
                        .setOrganiz(s.getOrganiz())
                        .setOrganizTel(s.getOPhone())
                        .setCellphone(s.getTel())
                        .setState(s.getIspass())
                        .setAgreement(s.getAgreement())
                        .setCreateTime(s.getCreateTime())
                        .setScore(s.getSore())
                        .setQrcode(DeclarerUtils.authofficerQrcode(s.getId().longValue()))
                ).collect(Collectors.toList());
    }

    public Integer count(JAuthofficerQueryParams params) {
        return this.authofficerMapper.count(params);
    }

    public void update(Long id, JAuthofficerParams params) {
        AssertUtils.notNull(id, new CustomException("参数错误"));
        this.validate(params);

        Authofficer authofficer = this.getById(id);
        AssertUtils.notNull(authofficer, new CustomException("参数错误"));

        this.update(authofficer
                .setAvator(params.getAvator())
                .setAgreement(params.getAgreement())
                .setIdCard(params.getIdCard())
                .setCardY(params.getIdFrontUrl())
                .setCardN(params.getIdBackUrl())
                .setName(params.getName())
                .setOrganiz(params.getOrganiz())
                .setOPhone(params.getOrganizTel())
                .setTel(params.getCellphone())
                .setSore(params.getScore())
                .setUpdateTime(System.currentTimeMillis())
        );
    }

    public List<Authofficer> getByName(String name) {
        return authofficerMapper.getByName(name);
    }

}