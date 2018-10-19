package com.cpsdb.declareserv.manager;

import com.cpsdb.base.common.AssertUtils;
import com.cpsdb.base.exception.CustomException;
import com.cpsdb.declareserv.dto.JOrder;
import com.cpsdb.declareserv.entity.Declarer;
import com.cpsdb.declareserv.entity.Order;
import com.cpsdb.declareserv.params.declarer.JInvoiceParams;
import com.cpsdb.declareserv.params.declarer.JPayParams;
import com.cpsdb.declareserv.service.DeclarerService;
import com.cpsdb.declareserv.service.OrderService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.payapi.api.PayApi;
import com.cpsdb.payapi.utils.PayMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderManager {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeclarerService declarerService;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public String pay(Long declarerId, JPayParams params, JInvoiceParams jinvoceParams) {
        PayMethod payMethod = PayMethod.get(params.getPayMethod());
        AssertUtils.notNull(payMethod, new CustomException("支付方式不正确"));

        Declarer declarer = declarerService.getById(declarerId);
        Order order = orderService.getByDeclarerId(declarer.getId(), null);
        AssertUtils.notNull(order, new CustomException("订单不存在"));
        if (DeclarerUtils.PayState.success.name().equals(order.getState())) {
            throw new CustomException("已经支付过了，不用再支付！");
        }
        //发票、更新订单
        return orderService.updatePay(params, order, payMethod, jinvoceParams);
    }

    public Boolean checkOrderSuccess(Long declarerId) {
        Order order = orderService.getByDeclarerId(declarerId, null);
        return DeclarerUtils.PayState.success.name().equals(order.getState()) && declarerId.equals(order.getFkDeclarerId());
    }

    public JOrder create(Long declarerId, String username) {
        //查询当前其是否存在未支付的订单
        Order order = orderService.getByDeclarerId(declarerId, DeclarerUtils.PayState.wait.name());

        //如果不存在则创建一个
        if (order == null) {
            order = orderService.createPay(declarerId);
        }

        return new JOrder()
                .setState(order.getState())
                .setAmount(order.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

}
