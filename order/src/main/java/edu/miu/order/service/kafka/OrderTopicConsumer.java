//package edu.miu.order.service.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
//
//
//
//@Service
//@RequiredArgsConstructor
//public class OrderTopicConsumer {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Value("${topic.name}")
//    private String topicName;
//
//    @KafkaListener(topics = "${topic.name}",groupId = "${spring.kafka.consumer.group-id}")
//    public void consume(ConsumerRecord<String, OrderStream> payload){
//        logger.warn("Consumed Order", payload);
//        logger.warn(topicName, payload);
//
//    }
//
//}
