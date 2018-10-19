package com.cpsdb.declareserv.service;

import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.DeclareEnterpriseMapper;
import com.cpsdb.declareserv.entity.DeclareEnterprise;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.params.declarer.JAuditParams;
import com.cpsdb.declareserv.params.declarer.JEnterpriseListParams;
import com.cpsdb.declareserv.params.declarer.JEnterpriseQueryParams;
import com.cpsdb.declareserv.params.declarer.OrganizEnterprisParams;
import com.cpsdb.declareserv.utils.DeclareEnterpriseUtils;
import com.cpsdb.redis.annotation.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @ClassName DeclareEnterpriseService
 * @Description
 * @author 李银
 * @date 2018-09-20 10:42:40
 */
@Service
public class DeclareEnterpriseService extends DeclareEnterpriseFsmService{

    @Autowired
    private DeclareEnterpriseMapper declareEnterpriseMapper;

    @Autowired
    private DeclarerService declarerService;

    public void insert(DeclareEnterprise declareEnterprise) {
        declareEnterpriseMapper.insert(declareEnterprise);
    }
    
    public void update(DeclareEnterprise declareEnterprise) {
declareEnterpriseMapper.update(declareEnterprise);
    }

    public DeclareEnterprise getById(Long id) {
        return declareEnterpriseMapper.getById(id);
    }

    public DeclareEnterprise getByCellPhone(String cellphone) {
        return declareEnterpriseMapper.getByCellPhone(cellphone);
    }

    public DeclareEnterprise getByIdNumber(String idNumber) {
        return declareEnterpriseMapper.getByIdNumber(idNumber);
    }

    @Lock(key = "create_declarer_enterprise",argsIndex = 0)
    public void insertInfo(User user, OrganizEnterprisParams organizEnterprisParams) {
        if (user.getType().equals(UserUtils.UserType.declarer.getValue())) {
            Declarer declarer = declarerService.getById(user.getObjectId());
            DeclareEnterprise declareEnterprise = new DeclareEnterprise()
                    .setName(organizEnterprisParams.getName())
                    .setCharger(organizEnterprisParams.getCharger())
                    .setCellphone(organizEnterprisParams.getCellphone())
                    .setIdNumber(organizEnterprisParams.getIdNumber())
                    .setIdFrontUrl(organizEnterprisParams.getIdFrontUrl())
                    .setIdBackUrl(organizEnterprisParams.getIdBackUrl())
                    .setLicenseImageUrl(organizEnterprisParams.getLicenseImageUrl())
                    .setProductionImageUrl(organizEnterprisParams.getProductionImageUrl())
                    .setCapitalImageUrl(organizEnterprisParams.getCapitalImageUrl())
                    .setEnterpriseChargerSurveyImageUrl(organizEnterprisParams.getEnterpriseChargerSurveyImageUrl())
                    .setEnterpriseShindImageUrl(organizEnterprisParams.getEnterpriseShindImageUrl())
                    .setEnterpriseSurveyImageUrl(organizEnterprisParams.getEnterpriseSurveyImageUrl())
                    .setAuthorizationImageUrl(organizEnterprisParams.getAuthorizationImageUrl())
                    .setOtherImageUrl(organizEnterprisParams.getOtherImageUrl())
                    .setFkDeclarerId(declarer.getId())
                    .setFkProvenceCenterId(declarer.getFkProvenceCenterId())
                    .setFkOrganizId(declarer.getFkOrganizId())
                    .setFkUserId(user.getId());
            this.insert(declareEnterprise);
            this.processEvent(declareEnterprise.getId(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.create, DeclareHolder.getUsername(), null);
        }
    }

    @Override
    protected void onStateChanged(Long id, String stateTo, String mark) {


    }

    public void updateInfo(String username, DeclareEnterprise declareEnterprie, OrganizEnterprisParams organizEnterprisParams) {
        declareEnterprie
                .setName(organizEnterprisParams.getName())
                .setCharger(organizEnterprisParams.getCharger())
                .setCellphone(organizEnterprisParams.getCellphone())
                .setIdNumber(organizEnterprisParams.getIdNumber())
                .setIdFrontUrl(organizEnterprisParams.getIdFrontUrl())
                .setIdBackUrl(organizEnterprisParams.getIdBackUrl())
                .setLicenseImageUrl(organizEnterprisParams.getLicenseImageUrl())
                .setProductionImageUrl(organizEnterprisParams.getProductionImageUrl())
                .setCapitalImageUrl(organizEnterprisParams.getCapitalImageUrl())
                .setEnterpriseChargerSurveyImageUrl(organizEnterprisParams.getEnterpriseChargerSurveyImageUrl())
                .setEnterpriseShindImageUrl(organizEnterprisParams.getEnterpriseShindImageUrl())
                .setEnterpriseSurveyImageUrl(organizEnterprisParams.getEnterpriseSurveyImageUrl())
                .setAuthorizationImageUrl(organizEnterprisParams.getAuthorizationImageUrl())
                .setOtherImageUrl(organizEnterprisParams.getOtherImageUrl());
        this.update(declareEnterprie);
        this.processEvent(declareEnterprie.getId(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm, username, null);
    }

    public List<DeclareEnterprise> queryDeclarer(JEnterpriseListParams params, DatagridPager pager) {
        return declareEnterpriseMapper.queryDeclarer(params, pager);
    }

    public List<DeclareEnterprise> queryList(JEnterpriseQueryParams params, DatagridPager pager) {
        return declareEnterpriseMapper.queryList(params, pager);
    }

    public void updateWaitAudit(DeclareEnterprise enterprise, String username, Boolean state, String reason) {

        this.update(enterprise.setReason(reason));
        if (state) {
            this.processEvent(enterprise.getId(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm, username, enterprise.getName());
        } else {
            this.processEvent(enterprise.getId(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.reject, username, reason);
        }

    }

    public void updateAudit(Long id, JAuditParams jAuditParams) {
        String username = PlatformUserHolder.getUsername();
        DeclareEnterprise declareEnterprise = this.getById(id);
        this.update(declareEnterprise.setReason(jAuditParams.getReason()));
        if (jAuditParams.getState()) {
            this.processEvent(id, DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm, username, declareEnterprise.getName());
        } else {
            this.processEvent(id, DeclareEnterpriseUtils.DeclareEnterpriseEvent.reject, username, jAuditParams.getReason());
        }

    }

    public Long getEnterpriseCount(JEnterpriseListParams params) {
        return declareEnterpriseMapper.getEnterpriseCount(params);
    }

    public void updateOrganiz(Long declarerId, Long organizId) {
        this.declareEnterpriseMapper.updateOrganiz(declarerId, organizId);
    }
}