package com.cpsdb.declareserv.mq.handler;

import com.cpsdb.declareserv.service.OrderService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.mq.consumer.factory.DefaultMQPushConsumerFactory;
import com.cpsdb.payapi.message.consumer.PayCallbackMessageHandler;
import com.cpsdb.payapi.params.PayCallback;
import com.cpsdb.payapi.utils.BusinessType;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李银 on 2018年3月13日 23:23:02
 * 申报官注册费用回调
 */
@Service
public class DeclarerStorageMessageHandler extends PayCallbackMessageHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Override
    protected BusinessType getBusinessType() {
        return BusinessType.declarer;
    }

    @Override
    public ConsumeConcurrentlyStatus process(PayCallback data, MessageExt message, ConsumeConcurrentlyContext context) {
        logger.info("receive pay paid notify message: {}", data.toString());
        try {
            if (data.getSuccess()) {
                orderService.doPay(data.getBusinessId(), data.getSuccess(), data.getReason());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("执行失败，可能已经处理过此消息了", e);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public String getConsumerGroup() {
        return DeclarerUtils.DECLARE_CONSUMER_GROUP;
    }
}
