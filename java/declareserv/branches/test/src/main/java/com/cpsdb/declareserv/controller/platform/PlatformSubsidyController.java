package com.cpsdb.declareserv.controller.platform;

import com.cpsdb.base.common.CPSDateUtils;
import com.cpsdb.base.controller.BaseController;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.base.pager.DatagridPager;
import com.cpsdb.baseservapi.annotation.GetPermission;
import com.cpsdb.baseservapi.holder.PlatformUserHolder;
import com.cpsdb.baseservapi.utils.UserType;
import com.cpsdb.declareserv.dto.JPlatformWithdrewRcord;
import com.cpsdb.declareserv.dto.JPlatformWithdrewRcordAmounts;
import com.cpsdb.declareserv.params.subsidy.JSubsidyWithdrawQueryParams;
import com.cpsdb.declareserv.service.SubsidyWithdrawRequestService;
import com.cpsdb.declareserv.utils.SubsidyWithdrawUtils;
import com.cpsdb.excel.config.Header;
import com.cpsdb.excel.writer.XmlExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 李银
 * @ClassName PlatformSubsidyController
 * @Description:
 * @date: 2018年9月20日 17:13:12
 */
@RestController
@RequestMapping("platform/subsidy")
public class PlatformSubsidyController extends BaseController {
    @Autowired
    private SubsidyWithdrawRequestService subsidyWithdrawRequestService;

    /**
     * @Description 提现记录列表
     */
    @GetMapping("list")
    List<JPlatformWithdrewRcord> list(JSubsidyWithdrawQueryParams params, DatagridPager pager) {
        return this.subsidyWithdrawRequestService.query(params, pager)
                .stream()
                .map(s -> new JPlatformWithdrewRcord()
                        .setSn(s.getId())
                        .setAccount(s.getAccount())
                        .setAmount(s.getAmount())
                        .setBank(s.getBank())
                        .setBankName(s.getBankName())
                        .setCreateTime(s.getCreateTime())
                        .setName(s.getName())
                        .setReason(s.getReason())
                        .setState(s.getState())
                        .setUsername(s.getUsername())
                        .setUserType(s.getUserType())
                        .setCellphone(s.getCellphone())
                )
                .collect(Collectors.toList());
    }

    /**
     * @Description 提现计算总数
     */
    @GetMapping("count")
    Integer count(JSubsidyWithdrawQueryParams params) {
        return this.subsidyWithdrawRequestService.count(params);
    }

    /**
     * @Description 提现金额统计
     */
    @GetMapping("amount")
    List<JPlatformWithdrewRcordAmounts> count() {
        return this.subsidyWithdrawRequestService.amount();
    }


    /**
     * @Description 下载申请记录
     */
    @GetMapping("downloadexcel")
    @GetPermission
    void download(HttpServletResponse response, JSubsidyWithdrawQueryParams params, DatagridPager pager) throws IOException {
        //获取下载数据
        List<JPlatformWithdrewRcord> records = this.list(params, pager);

        //得到相应的数据
        List<Header> headerList = Header.of(
                Header.of("sn", "交易号"),
                Header.of("createTime", "申请时间"),
                Header.of("amount", "申请金额"),
                Header.of("account", "银行卡号"),
                Header.of("state", "补贴状态")
                        .setDataFormatter(s -> SubsidyWithdrawUtils.State.getCn(((JPlatformWithdrewRcord) s).getState()))
        );

        String systemName = "申请记录-" + CPSDateUtils.toString(new Date());
        systemName = new String(systemName.getBytes(), "ISO8859-1");

        //得到响应流并发送
        response.reset();
        try (OutputStream out = response.getOutputStream()) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + systemName + ".xls\"");
            response.setContentType("application/octet-stream;charset=UTF-8");

            try (XmlExcel excel = XmlExcel.of(out, headerList)
                    .start()
                    .appendBody(records)
                    .finish()) {
            }
            out.flush();
        } catch (Exception e) {
            this.logger.error("下载提现申请失败", e);
            throw new CustomException("下载提现申请失败");
        }
    }

    /**
     * @Description 审核通过接口
     */
    @PostMapping("confirm/{subsidyId}")
    @GetPermission(permission = {UserType.center})
    public void confirm(@PathVariable Long subsidyId, String reason) {
        subsidyWithdrawRequestService.processEvent(subsidyId,
                SubsidyWithdrawUtils.Event.confirm, PlatformUserHolder.getUsername(), reason);
    }

    /**
     * @Description 审核不通过接口
     */
    @PostMapping("reject/{subsidyId}")
    @GetPermission(permission = {UserType.center})
    public void reject(@PathVariable Long subsidyId, @RequestParam(value = "reason") String reason) {
        subsidyWithdrawRequestService.processEvent(subsidyId,
                SubsidyWithdrawUtils.Event.reject, PlatformUserHolder.getUsername(), reason);
    }

    /**
     * @param subsidyId
     * @param reason
     * @Description 审核延后接口
     */
    @PostMapping("delay/{subsidyId}")
    @GetPermission(permission = {UserType.center})
    public void delay(@PathVariable Long subsidyId, @RequestParam(value = "reason") String reason) {
        subsidyWithdrawRequestService.processEvent(subsidyId,
                SubsidyWithdrawUtils.Event.delay, PlatformUserHolder.getUsername(), reason);
    }

}
