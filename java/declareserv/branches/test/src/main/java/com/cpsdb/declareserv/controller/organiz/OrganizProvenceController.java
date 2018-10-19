package com.cpsdb.declareserv.controller.organiz;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dto.JProvenceCenterDetail;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.manager.ProvenceManager;
import com.cpsdb.declareserv.params.provence.JProvenceCenterEditParams;
import com.cpsdb.declareserv.service.ProvenceCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 老周
 * @ClassName OrganizProvenceController
 * @Description
 * @date: 2018-09-20 10:40:38
 */
@RestController
@RequestMapping("organiz/provence")
public class OrganizProvenceController extends BaseController {

    @Autowired
    private ProvenceManager provenceManager;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    /**
     * @Description 省级服务中心修改
     */
    @PutMapping("")
    public void update(JProvenceCenterEditParams params) {
        Long objectId = DeclareHolder.getObjectId();
        AssertUtils.isTrue(UserUtils.UserType.service == DeclareHolder.getUserType(), new CustomException("您没有该权限！"));
        this.provenceCenterService.editProvence(objectId, params);
    }

    /**
     * @Description 省级服务中心详情
     * @Required id
     */
    @GetMapping("")
    public JProvenceCenterDetail details() {
        Long objectId = DeclareHolder.getObjectId();
        ProvenceCenter details = provenceManager.getDetails(objectId);
        return new JProvenceCenterDetail()
                .setCompanyName(details.getCompanyName())
                .setIdNumber(details.getIdNumber())
                .setType(details.getType())
                .setState(details.getState())
                .setRecommend(details.getRecommend())
                .setIdFrontUrl(details.getIdFrontUrl())
                .setIdBackUrl(details.getIdBackUrl())
                .setChargerName(details.getChargerName())
                .setChargerInvestigationUrl(details.getChargerInvestigationUrl())
                .setCompanyInvestigationUrl(details.getCompanyInvestigationUrl())
                .setAreaCode(details.getAreaCode())
                .setApplyLetterUrl(details.getApplyLetterUrl())
                .setBusinessLicenseUrl(details.getBusinessLicenseUrl())
                .setId(details.getId())
                .setCellphone(details.getCellphone())
                .setPromiseLetterUrl(details.getPromiseLetterUrl())
                .setAddress(details.getAddress())
                .setReason(details.getReason())
                .setCreateTime(details.getCreateTime())
                .setModifyTime(details.getModifyTime());


    }


}

