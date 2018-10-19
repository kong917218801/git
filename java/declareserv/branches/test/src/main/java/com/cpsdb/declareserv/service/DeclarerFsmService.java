package com.cpsdb.declareserv.service;

import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.dao.DeclarerMapper;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.utils.DeclarerUtils.DeclarerEvent;
import com.cpsdb.declareserv.utils.DeclarerUtils.DeclarerState;
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
public class DeclarerFsmService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected DeclarerMapper declarerMapper;

    @Autowired
    protected OperationLogService operationLogService;

    /**
     * 状态上下文
     */
    private static String[][] state_context = {
            {null, DeclarerEvent.create.name(), DeclarerState.waitPay.name()},
            {null, DeclarerEvent.pay.name(), DeclarerState.waitAuthen.name()},
            //待支付——》支付——》认证信息待填写
            {DeclarerState.waitPay.name(), DeclarerEvent.pay.name(), DeclarerState.waitAuthen.name()},
            //认证信息待填写——》提交——》财务审核
            {DeclarerState.waitAuthen.name(), DeclarerEvent.submit.name(), DeclarerState.financeAudit.name()},

            //财务审核——》不通过——》待支付
            {DeclarerState.financeAudit.name(), DeclarerEvent.reject.name(), DeclarerState.waitPay.name()},
            //财务审核——》通过——》待初审
            {DeclarerState.financeAudit.name(), DeclarerEvent.confirm.name(), DeclarerState.waitPending.name()},

            {DeclarerState.unpass.name(), DeclarerEvent.submit.name(), DeclarerState.waitPending.name()},

            {DeclarerState.waitPending.name(), DeclarerEvent.reject.name(), DeclarerState.unpass.name()},
            {DeclarerState.waitPending.name(), DeclarerEvent.confirm.name(), DeclarerState.waitAudit.name()},

            {DeclarerState.unpass.name(), DeclarerEvent.reject.name(), DeclarerState.unpass.name()},
            {DeclarerState.waitAudit.name(), DeclarerEvent.reject.name(), DeclarerState.unpass.name()},

            {DeclarerState.unpass.name(), DeclarerEvent.confirm.name(), DeclarerState.waitPending.name()},
            {DeclarerState.waitAudit.name(), DeclarerEvent.confirm.name(), DeclarerState.passed.name()},
            {DeclarerState.delete.name(), DeclarerEvent.confirm.name(), DeclarerState.deleted.name()},

            {DeclarerState.passed.name(), DeclarerEvent.delete.name(), DeclarerState.delete.name()},
            {DeclarerState.delete.name(), DeclarerEvent.delete.name(), DeclarerState.deleted.name()},
    };

    private static String _processEvent(String state, String event) {
        for (String[] sc : state_context) {
            if (StringUtils.equals(state, sc[0]) && StringUtils.equals(event, sc[1])) {
                return sc[2];
            }
        }

        String notice = String.format("当前状态[%s]不能进行[%s]操作！", DeclarerState.get(state), DeclarerEvent.getCn(event));
        throw new CustomException(notice);
    }

    public void processEvent(Long id, DeclarerEvent event, String user, String mark) {
        Declarer declarer = this.declarerMapper.getById(id);

        this.logger.info("申报官id={}；执行事件={}；当前状态={}；执行人={}；备注={}", id, event.name(), declarer.getState(), user, mark);

        String stateFrom = declarer.getState();
        String stateTo = _processEvent(declarer.getState(), event.name());

        this.declarerMapper.updateState(id, stateTo);

        onStateChanged(id, stateFrom, event, stateTo, mark);

        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.declarer, declarer.getId(),
                stateFrom, event.name(), stateTo, user, mark);

    }

    /**
     * 当到达此状态的时候，各个子类需要处理的事
     */
    protected void onStateChanged(Long id, String stateFrom, DeclarerEvent event, String stateTo, String mark) {
        throw new RuntimeException("not implements yet!");
    }

    public void insertLog(Long declareId, DeclarerEvent event, String user, String mark) {
        Declarer declarer = this.declarerMapper.getById(declareId);
        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.declarer, declareId,
                declarer.getState(), event.name(), declarer.getState(), user, mark);
    }
}