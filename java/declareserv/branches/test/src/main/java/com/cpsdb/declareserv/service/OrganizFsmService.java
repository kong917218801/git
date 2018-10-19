package com.cpsdb.declareserv.service;

import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.dao.OrganizMapper;
import com.cpsdb.declareserv.entity.Organiz;
import com.cpsdb.declareserv.utils.OrganizUtils.OrganizEvent;
import com.cpsdb.declareserv.utils.OrganizUtils.OrganizState;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class OrganizFsmService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected OrganizMapper organizMapper;

    @Autowired
    protected OperationLogService operationLogService;

    /**
     * 状态上下文
     */
    private static String[][] state_context = new String[][]{
            //null + 创建 -> 基本信息待完善
            {null, OrganizEvent.create.name(), OrganizState.baseWaitSubmit.name()},

            //基本信息待完善 + 提交 -> 财务审核初审
            {OrganizState.baseWaitSubmit.name(), OrganizEvent.submit.name(), OrganizState.financeAudit.name()},

            //财务审核初审 + 审核 -> 基本信息复初审
            {OrganizState.financeAudit.name(), OrganizEvent.confirm.name(), OrganizState.baseWaitPending.name()},

            //基本信息待初审 + 审核 -> 基本信息复复审
            {OrganizState.baseWaitPending.name(), OrganizEvent.confirm.name(), OrganizState.baseWaitAudit.name()},
            //基本信息复复审 + 审核 -> 登记信息待填写
            {OrganizState.baseWaitAudit.name(), OrganizEvent.confirm.name(), OrganizState.registWaitSubmit.name()},

            //基本信息待初审 + 不通过 -> 基本信息审核不通过   基本信息初审不通过
            {OrganizState.baseWaitPending.name(), OrganizEvent.reject.name(), OrganizState.baseUnPass.name()},
            //基本信息复初审 + 不通过 -> 基本信息审核不通过   基本信息复审不通过
            {OrganizState.baseWaitAudit.name(), OrganizEvent.reject.name(), OrganizState.baseUnPass.name()},
            //基本信息审核不通过 + 提交 -> 基本信息待初审
            {OrganizState.baseUnPass.name(), OrganizEvent.submit.name(), OrganizState.baseWaitPending.name()},
            {OrganizState.baseWaitPending.name(), OrganizEvent.submit.name(), OrganizState.baseWaitPending.name()},


            //登记信息待填写 + 提交 -> 登记信息待初审
            {OrganizState.registWaitSubmit.name(), OrganizEvent.submit.name(), OrganizState.registWaitPending.name()},
            //登记信息待初审 + 审核 -> 登记信息待复审
            {OrganizState.registWaitPending.name(), OrganizEvent.confirm.name(), OrganizState.registWaitAudit.name()},
            //登记信息待复审 + 审核 -> 审核通过(完结)
            {OrganizState.registWaitAudit.name(), OrganizEvent.confirm.name(), OrganizState.passed.name()},

            //登记信息待初审 + 不通过 -> 登记信息未通过
            {OrganizState.registWaitPending.name(), OrganizEvent.reject.name(), OrganizState.registUnPass.name()},
            //登记信息待复审 + 不通过 -> 登记信息未通过
            {OrganizState.registWaitAudit.name(), OrganizEvent.reject.name(), OrganizState.registUnPass.name()},
            //登记信息未通过 + 提交 -> 登记信息待填写
            {OrganizState.registUnPass.name(), OrganizEvent.submit.name(), OrganizState.registWaitPending.name()},

            //任意状态 + 删除 -> 已删除
            {"*", OrganizEvent.delete.name(), OrganizState.delete.name()}
    };

    private static String _processEvent(String state, String event) {
        for (String[] sc : state_context) {
            if ((StringUtils.equals(state, sc[0]) || "*".equals(sc[0])) && StringUtils.equals(event, sc[1])) {
                return sc[2];
            }
        }

        String notice = String.format("当前状态[%s]不能进行[%s]操作！", OrganizState.getCn(state), OrganizEvent.getCn(event));
        throw new CustomException(notice);
    }

    public void processEvent(Long id, OrganizEvent event, String user, String mark) {
        Organiz organiz = this.organizMapper.getById(id);

        this.logger.info("申报服务处id={}；执行事件={}；当前状态={}；执行人={}；备注={}", id, event.name(), organiz.getState(), user, mark);

        String stateFrom = organiz.getState();
        String stateTo = _processEvent(stateFrom, event.name());
        this.organizMapper.updateState(id, stateTo);

        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.organiz, organiz.getId(),
                stateFrom, event.name(), stateTo, user, mark);

        onStateChanged(id, stateFrom, event, stateTo, mark);
    }

    /**
     * 当到达此状态的时候，各个子类需要处理的事
     */
    protected abstract void onStateChanged(Long id, String stateFrom, OrganizEvent event, String stateTo, String mark);

    public void insertLog(Long declareId, OrganizEvent event, String user, String mark) {
        Organiz organiz = this.organizMapper.getById(declareId);
        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.organiz, declareId,
                organiz.getState(), event.name(), organiz.getState(), user, mark);
    }
}
