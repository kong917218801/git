package com.cpsdb.declareserv.mq.handler;

import com.cpsdb.declareserv.service.DeclareToEnterpriseService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.enterpriseapi.mq.consumer.MqEnterpriseMessageHandler;
import com.cpsdb.enterpriseapi.utils.MQEnterprise;
import com.cpsdb.mq.consumer.factory.DefaultMQPushConsumerFactory;
import com.cpsdb.redis.annotation.Lock;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class DeclarerEnterpriseStateMessageHandler extends MqEnterpriseMessageHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeclareToEnterpriseService declareToEnterpriseService;

    @Override
    @Lock(key = "MQ_ENTERPRISE_STATE",argsIndex = 0)
    public ConsumeConcurrentlyStatus process(MQEnterprise data, MessageExt message, ConsumeConcurrentlyContext context) {

        logger.info("收到mq发送的消息 {}", data);

        try {
            declareToEnterpriseService.updateMqEnterpriseToDeclare(data);

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
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
