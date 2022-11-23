package edu.miu.order.service.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderTopicProducer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final KafkaTemplate<String, OrderStream> kafkaTemplate;
    public void send(String topicName,OrderStream orderStream){
        logger.warn("Produced OrderStream: {}", orderStream);
        logger.warn(topicName);
        kafkaTemplate.send(topicName, orderStream);
    }


}

