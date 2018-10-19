package com.cpsdb.declareserv.service;

import com.cpsdb.declareserv.dao.OrderMapper;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.Invoice;
import com.cpsdb.declareserv.entity.Order;
import com.cpsdb.declareserv.params.declarer.JInvoiceParams;
import com.cpsdb.declareserv.params.declarer.JPayParams;
import com.cpsdb.declareserv.utils.DeclareConfigUtils;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.payapi.api.PayApi;
import com.cpsdb.payapi.params.PayRequest;
import com.cpsdb.payapi.utils.BusinessType;
import com.cpsdb.payapi.utils.PayMethod;
import com.cpsdb.redis.annotation.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 李银
 * @ClassName OrderService
 * @Description
 * @date 2018-09-20 10:54:46
 */
@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DeclarerService declarerService;

    @Autowired
    private PayApi payApi;

    public Order insert(Order order) {
        orderMapper.insert(order);
        return order;
    }

    public void update(Order order) {
        orderMapper.update(order);
    }

    public Order getById(Long id) {
        return orderMapper.getById(id);
    }

    public Order getByDeclarerId(Long declarerId, String state) {
        return orderMapper.getByDeclarerId(declarerId, state);
    }

    public void updateInvoice(Order order, JInvoiceParams params) {

        Invoice invoice = invoiceService.getByOrderId(order.getId());
        if (invoice == null) {
            Invoice invoice1 = new Invoice()
                    .setInvoiceType(params.getInvoiceType())
                    .setName(params.getName())
                    .setFkOrderId(order.getId())
                    .setRevenueNumber(params.getRevenueNumber())
                    .setEmail(params.getEmail())
                    .setCellphone(params.getCellphone());
            invoiceService.insert(invoice1);
        } else {
            invoice.setInvoiceType(params.getInvoiceType())
                    .setName(params.getName())
                    .setFkOrderId(order.getId())
                    .setRevenueNumber(params.getRevenueNumber())
                    .setEmail(params.getEmail())
                    .setCellphone(params.getCellphone());
            invoiceService.update(invoice);
        }
        orderMapper.update(order);
    }

    @Lock(key = "DECLARER_PAY", argsIndex = 0)
    public Order createPay(Long declarerId) {

        Order order = new Order()
                .setAmount(DeclareConfigUtils.getBigDecimal("pay.declarer.amount"))
                .setFkDeclarerId(declarerId)
                .setState(DeclarerUtils.PayState.wait.name());

        return this.insert(order);
    }

    public void doPay(Long businessId, boolean success, String reason) {
        {
            Order order = this.getById(businessId);
            if (null == order) {
                return;
            }
            if (success) {
                // 支付成功
                order.setState(DeclarerUtils.PayState.success.name())
                        .setPayTime(new Date());
                this.update(order);

                Declarer declarer = declarerService.getById(order.getFkDeclarerId());
                String mark = PayMethod.get(order.getPayMethod()).getValue();
                declarerService.processEvent(declarer.getId(), DeclarerUtils.DeclarerEvent.pay, declarer.getName(), mark);
            } else {
                // 支付失败
                order.setState(DeclarerUtils.PayState.failed.name())
                        .setPayTime(new Date())
                        .setReason(reason);
                this.update(order);
            }
        }
    }

    public void createByOrganiz(Long declarerId) {
        Order order = new Order()
                .setPayMethod(PayMethod.free.name())
                .setPayTime(new Date())
                .setAmount(BigDecimal.ZERO)
                .setFkDeclarerId(declarerId)
                .setState(DeclarerUtils.PayState.success.name());
        this.insert(order);
    }

    public String updatePay(JPayParams params, Order order, PayMethod payMethod, JInvoiceParams jinvoceParams) {

        //保存或者更新发票
        if (params.getNeedInvoice()) {
            this.updateInvoice(order, jinvoceParams);
        }


        order.setPayMethod(payMethod.name());
        this.update(order);

        //现金线下支付
        if (payMethod == PayMethod.offline) {
            this.doPay(order.getId(), true, "现金支付");
            return "success";
        }
        return payApi.pay(new PayRequest()
                .setPayMethod(params.getPayMethod())
                .setBusinessType(BusinessType.declarer.name())
                .setBusinessId(order.getId())
                .setAmount(order.getAmount())
                .setSubject("申报官认证费用（包含服装、工牌等）")
                .setBody("申报官认证费用（包含服装、工牌等）")
                .setReturnUrl(params.getReturnPage())
                .setClientIP(params.getClientIP()));
    }
}