package com.cpsdb.declareserv.service;

import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.dao.DeclareEnterpriseMapper;
import com.cpsdb.declareserv.entity.DeclareEnterprise;
import com.cpsdb.declareserv.utils.DeclareEnterpriseUtils;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 李银
 * @ClassName CodeService
 * @Description
 * @date 2017-11-30 18:20:33
 */
public abstract class DeclareEnterpriseFsmService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected DeclareEnterpriseMapper declareEnterpriseMapper;

    @Autowired
    protected OperationLogService operationLogService;

    /**
     * 状态上下文
     */
    private static String[][] state_context = new String[][]{

            {null, DeclareEnterpriseUtils.DeclareEnterpriseEvent.create.name(), DeclareEnterpriseUtils.EnterpriseState.waitPending.name()},

            {DeclareEnterpriseUtils.EnterpriseState.waitPending.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm.name(), DeclareEnterpriseUtils.EnterpriseState.waitAudit.name()},
            {DeclareEnterpriseUtils.EnterpriseState.waitUnPending.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm.name(), DeclareEnterpriseUtils.EnterpriseState.waitPending.name()},
            {DeclareEnterpriseUtils.EnterpriseState.unPass.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm.name(), DeclareEnterpriseUtils.EnterpriseState.waitPending.name()},
            {DeclareEnterpriseUtils.EnterpriseState.waitAudit.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm.name(), DeclareEnterpriseUtils.EnterpriseState.pass.name()},
            {DeclareEnterpriseUtils.EnterpriseState.waitUnPending.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.confirm.name(), DeclareEnterpriseUtils.EnterpriseState.waitPending.name()},

            {DeclareEnterpriseUtils.EnterpriseState.waitPending.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.reject.name(), DeclareEnterpriseUtils.EnterpriseState.waitUnPending.name()},
            {DeclareEnterpriseUtils.EnterpriseState.waitUnPending.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.reject.name(), DeclareEnterpriseUtils.EnterpriseState.waitUnPending.name()},
            {DeclareEnterpriseUtils.EnterpriseState.waitAudit.name(), DeclareEnterpriseUtils.DeclareEnterpriseEvent.reject.name(), DeclareEnterpriseUtils.EnterpriseState.unPass.name()},
    };

    private static String _processEvent(String state, String event) {
        for (String[] sc : state_context) {
            if (StringUtils.equals(state, sc[0]) && StringUtils.equals(event, sc[1])) {
                return sc[2];
            }
        }

        String notice = String.format("当前状态[%s]不能进行[%s]操作！", DeclareEnterpriseUtils.EnterpriseState.getState(state), DeclareEnterpriseUtils.DeclareEnterpriseEvent.getCn(event));
        throw new CustomException(notice);
    }

    public void processEvent(Long id, DeclareEnterpriseUtils.DeclareEnterpriseEvent event, String user, String mark) {
        DeclareEnterprise enterprise = this.declareEnterpriseMapper.getById(id);

        this.logger.info("申报企业id={}；执行事件={}；当前状态={}；执行人={}；备注={}", id, event.name(), enterprise.getState(), user, mark);

        String stateTo = _processEvent(enterprise.getState(), event.name());
        this.declareEnterpriseMapper.updateState(id, stateTo);

        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.declareEnterprise, enterprise.getId(),
                enterprise.getState(), event.name(), stateTo, user, mark);

        onStateChanged(id, stateTo, mark);
    }

    /**
     * 当到达此状态的时候，各个子类需要处理的事
     */
    protected abstract void onStateChanged(Long id, String stateTo, String mark);

    public void insertLog(Long declareEnterpriseId, DeclareEnterpriseUtils.DeclareEnterpriseEvent event, String user, String mark) {
        DeclareEnterprise enterprise = this.declareEnterpriseMapper.getById(declareEnterpriseId);
        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.declareEnterprise, declareEnterpriseId,
                enterprise.getState(), event.name(), enterprise.getState(), user, mark);
    }

}