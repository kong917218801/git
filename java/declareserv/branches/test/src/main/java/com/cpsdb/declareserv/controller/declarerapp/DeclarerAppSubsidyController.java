package com.cpsdb.declareserv.controller.declarerapp;

import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.declareapi.holder.DeclareHolder;
import com.cpsdb.declareserv.dto.JAppWithdrewRcord;
import com.cpsdb.declareserv.dto.JSubsidyBank;
import com.cpsdb.declareserv.dto.JSubsidyDetail;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.SubsidyBank;
import com.cpsdb.declareserv.params.subsidy.JSubsidyBankParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyDetailQueryParams;
import com.cpsdb.declareserv.params.subsidy.JSubsidyWithdrawQueryParams;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.service.SubsidyBankService;
import com.cpsdb.declareserv.service.SubsidyDetailService;
import com.cpsdb.declareserv.service.SubsidyWithdrawRequestService;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 李银
 * @ClassName DeclarerAppSubsidyController
 * @Description:
 * @date: 2018年9月20日 17:13:12
 */

@RestController
@RequestMapping("declarerapp/subsidy")
public class DeclarerAppSubsidyController extends BaseController {

    @Autowired
    private SubsidyDetailService subsidyDetailService;

    @Autowired
    private SubsidyWithdrawRequestService subsidyWithdrawRequestService;

    @Autowired
    private SubsidyBankService subsidyBankService;

    @Autowired
    private DeclarerService declarerService;

    /**
     * @Description 申报官APP获取可提现金额
     */
    @GetMapping("amount")
    String amount() {
        return subsidyDetailService.getUnwithdrawedAmount(DeclareHolder.getUserId()).toString();
    }

    /**
     * @Description 申报官APP可提现的补贴明细
     */
    @GetMapping("detail")
    List<JSubsidyDetail> detail(DatagridPager pager) {
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


    public static void main(String[] args) {
        System.out.println(new BigDecimal(30731.000).multiply(new BigDecimal(0.3)).setScale(3,RoundingMode.CEILING).toString());
    }
    /**
     * @Description 申报官APP获取历史提现银行
     */
    @GetMapping("bank")
    public List<JSubsidyBank> bank() {
        return subsidyBankService.getHistory(DeclareHolder.getUserId());
    }

    /**
     * @Description 申报官APP申请提现
     */
    @PostMapping("")
    void subsidy(JSubsidyBankParams params) {
        Declarer declarer = declarerService.getById(DeclareHolder.getUserId());
        subsidyWithdrawRequestService.insert(DeclareHolder.getUserId(), DeclareHolder.getUserType(), declarer.getName(), params);
    }

    /**
     * @Description 申报官APP我的提现记录
     */
    @GetMapping("list")
    List<JAppWithdrewRcord> list(DatagridPager pager) {
        return this.subsidyWithdrawRequestService.query(new JSubsidyWithdrawQueryParams()
                .setUserId(DeclareHolder.getUserId()), pager)
                .stream()
                .map(s -> new JAppWithdrewRcord()
                        .setAmount((s.getAmount()).setScale(3, RoundingMode.CEILING).toString())
                        .setAccount(s.getAccount())
                        .setCreateTime(s.getCreateTime())
                        .setReason(s.getReason())
                        .setSn(s.getId())
                        .setState(s.getState())
                )
                .collect(Collectors.toList());
    }
}
