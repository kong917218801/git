package com.cpsdb.declareserv.service;

import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.dao.ProvenceCenterMapper;
import com.cpsdb.declareserv.entity.ProvenceCenter;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import com.cpsdb.declareserv.utils.ProvenceUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 老周
 * @Description 功能描述
 * @CreateDate 2018.7.1
 * @Version v1.0
 */
@Service
public abstract class ProvenceFsmService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ProvenceCenterMapper provenceCenterMapper;

    @Autowired
    protected OperationLogService operationLogService;
    /**
     * 状态上下文
     */
    private static String[][] state_context = new String[][]{

            {null, ProvenceUtils.ProvEvent.create.name(), ProvenceUtils.ProvenceState.financeAudit.name()},

            {ProvenceUtils.ProvenceState.financeAudit.name(), ProvenceUtils.ProvEvent.confirm.name(), ProvenceUtils.ProvenceState.waitPending.name()},

            {ProvenceUtils.ProvenceState.waitPending.name(), ProvenceUtils.ProvEvent.confirm.name(), ProvenceUtils.ProvenceState.waitAudit.name()},
            {ProvenceUtils.ProvenceState.waitPending.name(), ProvenceUtils.ProvEvent.reject.name(), ProvenceUtils.ProvenceState.unpass.name()},


            {ProvenceUtils.ProvenceState.waitAudit.name(), ProvenceUtils.ProvEvent.reject.name(), ProvenceUtils.ProvenceState.unpass.name()},
            {ProvenceUtils.ProvenceState.waitAudit.name(), ProvenceUtils.ProvEvent.confirm.name(), ProvenceUtils.ProvenceState.passed.name()},

            {ProvenceUtils.ProvenceState.unpass.name (), ProvenceUtils.ProvEvent.submit.name(), ProvenceUtils.ProvenceState.waitPending.name()},

            //任意状态 + 删除 -> 已删除
            {"*", ProvenceUtils.ProvEvent.delete.name(), ProvenceUtils.ProvenceState.deleting.name()}
    };

    private static String _processEvent(String state, String event) {
        for (String[] sc : state_context) {
            if ((StringUtils.equals(state, sc[0]) || "*".equals(sc[0])) && StringUtils.equals(event, sc[1])) {
                return sc[2];
            }
        }

        String notice = String.format("当前状态[%s]不能进行[%s]操作！", ProvenceUtils.ProvenceState.get(state), ProvenceUtils.ProvEvent.getCn(event));
        throw new CustomException(notice);
    }

    public void processEvent(Long id, ProvenceUtils.ProvEvent event, String user, String mark) {
        ProvenceCenter prov = this.provenceCenterMapper.getById(id);

        this.logger.info("申报机构id={}；执行事件={}；当前状态={}；执行人={}；备注={}", id, event.name(), prov.getState(), user, mark);

        String stateFrom = prov.getState();
        String stateTo = _processEvent(stateFrom, event.name());
        this.provenceCenterMapper.updateState(id, stateTo,mark);

        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.provence, prov.getId(),
                stateFrom, event.name(), stateTo, user, mark);

        onStateChanged(id, stateFrom, event, stateTo, mark);
    }


    /**
     * 当到达此状态的时候，各个子类需要处理的事
     */
    abstract protected void onStateChanged(Long id, String stateFrom, ProvenceUtils.ProvEvent event, String stateTo, String mark);



}
