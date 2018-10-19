package com.cpsdb.declareserv.controller.organiz;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareapi.utils.UserUtils;
import com.cpsdb.declareserv.dto.JOrganizWithdrewRcord;
import com.cpsdb.declareserv.dto.JSubsidyBank;
import com.cpsdb.declareserv.dto.JSubsidyDetail;
import com.cpsdb.declareserv.entity.User;
import com.cpsdb.declareserv.params.subsidy.JSubsidyBankParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyDetailQueryParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyWithdrawQueryParams;
import com.cpsdb.declareserv.service.*;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 李银
 * @ClassName OrganizSubsidyController
 * @Description
 * @date: 2018年9月20日 17:13:12
 */

@RestController
@RequestMapping("organiz/subsidy")
public class OrganizSubsidyController extends BaseController {

    @Autowired
    private SubsidyDetailService subsidyDetailService;

    @Autowired
    private SubsidyWithdrawRequestService subsidyWithdrawRequestService;

    @Autowired
    private SubsidyBankService subsidyBankService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private OrganizService organizService;

    @Autowired
    private ProvenceCenterService provenceCenterService;

    /**
     * @Description 获取可提现金额
     */
    @GetMapping("amount")
    BigDecimal amount() {
        return subsidyDetailService.getUnwithdrawedAmount(DeclareHolder.getUserId());
    }

    /**
     * @Description 可提现的补贴明细
     */
    @GetMapping("detail")
    List<JSubsidyDetail> detail(DatagridPager pager) {
        //pager = new DatagridPager(Integer.MAX_VALUE, 1);
        return subsidyDetailService.query(new JSubsidyDetailQueryParams()
                .setUserId(DeclareHolder.getUserId())
                .setState(SubsidyWithdrawUtils.DetailState.unwithdrawed.name()), pager)
                .stream().map(s -> new JSubsidyDetail()
                        .setAmount((s.getAmount().multiply(s.getPercent())).setScale(3, RoundingMode.CEILING).toString())
                        .setName(s.getName())
                        .setQuantity(s.getQuantity())
                        .setType(s.getType())
                )
                .collect(Collectors.toList());
    }

    /**
     * @Description 获取历史提现银行
     */
    @GetMapping("bank")
    public List<JSubsidyBank> bank() {
        return subsidyBankService.getHistory(DeclareHolder.getUserId());
    }

    /**
     * @Description 申请提现
     */
    @PostMapping("")
    void subsidy(JSubsidyBankParams params) {
        Long userId = DeclareHolder.getUserId();
        UserUtils.UserType userType = DeclareHolder.getUserType();
        String username = this.getUserName(userId,userType);
        subsidyWithdrawRequestService.insert(userId, userType, username, params);
    }

    private String getUserName(Long userId, UserUtils.UserType userType) {
        User user = userService.getById(userId);
        if (userType == UserUtils.UserType.declarer){
            return declarerService.getById(user.getObjectId()).getName();
        }else if (userType == UserUtils.UserType.organiz) {
            return organizService.getById(user.getObjectId()).getCompanyName();
        }else if (userType == UserUtils.UserType.service) {
            return provenceCenterService.getById(user.getObjectId()).getCompanyName();
        }else
        return null;
    }

    /**
     * @Description 我的提现记录
     */
    @GetMapping("list")
    List<JOrganizWithdrewRcord> list(DatagridPager pager) {
        return this.subsidyWithdrawRequestService.query(new JSubsidyWithdrawQueryParams()
                .setUserId(DeclareHolder.getUserId()), pager)
                .stream()
                .map(s -> new JOrganizWithdrewRcord()
                        .setAmount(s.getAmount())
                        .setAccount(s.getAccount())
                        .setCreateTime(s.getCreateTime())
                        .setReason(s.getReason())
                        .setSn(s.getId())
                        .setState(s.getState())
                )
                .collect(Collectors.toList());
    }

    /**
     * @Description 我的提现计算总数
     */
    @GetMapping("count")
    Integer count() {
        return this.subsidyWithdrawRequestService.count(
                new JSubsidyWithdrawQueryParams().setUserId(DeclareHolder.getUserId())
        );
    }
}
