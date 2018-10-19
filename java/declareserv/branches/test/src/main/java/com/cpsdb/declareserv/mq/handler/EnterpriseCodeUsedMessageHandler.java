package com.cpsdb.declareserv.mq.handler;

import com.cpsdb.declareserv.service.DeclareToEnterpriseService;
import com.cpsdb.declareserv.utils.DeclarerUtils;
import com.cpsdb.goodsapi.mq.consumer.CodeUsedMessageHandler;
import com.cpsdb.goodsapi.params.CodeUsed;
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
public class EnterpriseCodeUsedMessageHandler extends CodeUsedMessageHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeclareToEnterpriseService declareToEnterpriseService;

    @Override
    @Lock(key = "MQ_CODE_AMOUNT",argsIndex = 0)
    public ConsumeConcurrentlyStatus process(CodeUsed data, MessageExt message, ConsumeConcurrentlyContext context) {

        logger.info("收到mq发送的消息 {}", data);

        try {
            //更新企业用码量，另外还得调用subsidyDetail插入接口
            declareToEnterpriseService.updateCodeUsed(data);
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
