package com.cpsdb.declareserv.service;

import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dao.SubsidyWithdrawRequestMapper;
import com.cpsdb.declareserv.dto.JPlatformWithdrewRcordAmounts;
import com.cpsdb.declareserv.entity.SubsidyBank;
import com.cpsdb.declareserv.entity.SubsidyDetail;
import com.cpsdb.declareserv.entity.SubsidyWithdrawRequest;
import com.cpsdb.declareserv.params.subsidy.JSubsidyBankParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyDetailQueryParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyWithdrawQueryParams;
import com.cpsdb.declareserv.utils.OperationLogUtils;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils.DetailState;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils.Event;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils.State;
import com.cpsdb.thirdapi.api.JuHeApi;
import com.cpsdb.thirdapi.utils.ModeType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 李银
 * @ClassName SubsidyWithdrawRequestService
 * @Description
 * @date 2018-09-20 17:26:02
 */
@Service
public class SubsidyWithdrawRequestService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SubsidyWithdrawRequestMapper subsidyWithdrawRequestMapper;

    @Autowired
    private SubsidyBankService subsidyBankService;

    @Autowired
    private SubsidyDetailService subsidyDetailService;

    @Autowired
    private SubsidyWithdrawRequestItemService subsidyWithdrawRequestItemService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private JuHeApi juHeApi;

    public void insert(SubsidyWithdrawRequest subsidyWithdrawRequest) {
        subsidyWithdrawRequestMapper.insert(subsidyWithdrawRequest);
    }

    public void update(SubsidyWithdrawRequest subsidyWithdrawRequest) {
        subsidyWithdrawRequestMapper.update(subsidyWithdrawRequest);
    }

    public List<SubsidyWithdrawRequest> query(JSubsidyWithdrawQueryParams params, DatagridPager pager) {
        return this.subsidyWithdrawRequestMapper.query(params, pager);
    }

    public Integer count(JSubsidyWithdrawQueryParams params) {
        return this.subsidyWithdrawRequestMapper.count(params);
    }

    public SubsidyWithdrawRequest getById(Long id) {
        return subsidyWithdrawRequestMapper.getById(id);
    }

    public void insert(Long userId, UserUtils.UserType userType, String username, JSubsidyBankParams params) {
        juHeApi.vilidaty(ModeType.WITHAW, params.getCellphone(), params.getCode());

        // 获取或者插入提现银行历史
        SubsidyBank subsidyBank = subsidyBankService.insertOrGet(userId, params);

        // 查询所有可提现的明细
        List<SubsidyDetail> items = subsidyDetailService.query(
                new JSubsidyDetailQueryParams().setUserId(userId).setState(DetailState.unwithdrawed.name()),
                new DatagridPager(Integer.MAX_VALUE, 1));
        // 获取可提现总额
        BigDecimal amount = this.subsidyDetailService.getUnwithdrawedAmount(userId);

        // 插入提现申请
        SubsidyWithdrawRequest request = new SubsidyWithdrawRequest()
                .setUserId(userId)
                .setUserType(userType.getValue())
                .setAmount(amount)
                .setCellphone(params.getCellphone())
                .setFkSubsidyBankId(subsidyBank.getId())
                .setUsername(username);
        subsidyWithdrawRequestMapper.insert(request);

        // 插入提现申请明细
        this.subsidyWithdrawRequestItemService.insert(request.getId(), items);

        this.processEvent(request.getId(), Event.create, username, "");
    }

    /**
     * 状态上下文
     */
    private static String[][] state_context = new String[][]{
            {null, Event.create.name(), State.pending.name()},

            {State.pending.name(), Event.confirm.name(), State.passed.name()},
            {State.pending.name(), Event.reject.name(), State.rejected.name()},
            {State.pending.name(), Event.delay.name(), State.delayed.name()},

            {State.rejected.name(), Event.confirm.name(), State.passed.name()},
            {State.rejected.name(), Event.reject.name(), State.rejected.name()},
            {State.rejected.name(), Event.delay.name(), State.delayed.name()},

            {State.delayed.name(), Event.confirm.name(), State.passed.name()},
            {State.delayed.name(), Event.reject.name(), State.rejected.name()},
            {State.delayed.name(), Event.delay.name(), State.delayed.name()},
    };

    private static String _processEvent(String state, String event) {
        for (String[] sc : state_context) {
            if (StringUtils.equals(state, sc[0]) && StringUtils.equals(event, sc[1])) {
                return sc[2];
            }
        }

        String notice = String.format("当前状态[%s]不能进行[%s]操作！", State.getCn(state), Event.getCn(event));
        throw new CustomException(notice);
    }

    public void processEvent(Long id, Event event, String user, String mark) {
        SubsidyWithdrawRequest request = this.subsidyWithdrawRequestMapper.getById(id);

        this.logger.info("申请id={}；执行事件={}；当前状态={}；执行人={}；备注={}", id, event.name(), request.getState(), user, mark);

        String stateFrom = request.getState();
        String stateTo = _processEvent(stateFrom, event.name());

        //插入操作日志
        operationLogService.insert(OperationLogUtils.Type.subsidy, request.getId(),
                request.getState(), event.name(), stateTo, user, mark);

        onStateChanged(request, stateFrom, stateTo, mark);

        this.subsidyWithdrawRequestMapper.update(request.setState(stateTo).setReason(mark));
    }

    private void onStateChanged(SubsidyWithdrawRequest request, String stateFrom, String stateTo, String mark) {
        if (State.pending.name().equals(stateTo)) {

            this.subsidyDetailService.updateState(request.getId(), DetailState.withdrawing);
        } else if (State.passed.name().equals(stateTo)) {
            //更新detail的状态
            this.subsidyDetailService.updateState(request.getId(), DetailState.withdrawed);

            juHeApi.threadSend(request.getCellphone(), ModeType.SUBSiDY_PASS, mark);
        } else if (State.rejected.name().equals(stateTo)) {
            this.subsidyDetailService.updateState(request.getId(), DetailState.unwithdrawed);

            juHeApi.threadSend(request.getCellphone(), ModeType.SUBSiDY_UNPASS, mark);
        } else if (State.delayed.name().equals(stateTo)) {
            this.subsidyDetailService.updateState(request.getId(), DetailState.withdrawing);

            juHeApi.threadSend(request.getCellphone(), ModeType.SUBSiDY_DELAYED, mark);
        }
    }

    public List<JPlatformWithdrewRcordAmounts> amount() {
        return this.subsidyWithdrawRequestMapper.amount();
    }
}