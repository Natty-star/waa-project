package edu.miu.property.kafka;

import edu.miu.property.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderTopicConsumer {
    @Autowired
    PropertyService propertyService;


    @KafkaListener(topics = "${topic.orderCreated.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderCreated(ConsumerRecord<String, OrderStream> payload){
        OrderStream orderStream  = payload.value();
       log.warn("Order Create Topic Consumed", orderStream);
        System.out.println(orderStream.getProperty().getPropertyId());
      propertyService.updateStatus(orderStream.getProperty().getPropertyId());



    }



    @KafkaListener(topics = "${topic.orderCanceled.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderCanceled(ConsumerRecord<String, OrderStream> payload){
        OrderStream orderStream  = payload.value();
        log.warn("Order Canceled Topic Consumed", orderStream);

        propertyService.updateStatus(orderStream.getProperty().getPropertyId());




    }

    @KafkaListener(topics = "${topic.orderDeclined.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderDeclined(ConsumerRecord<String, OrderStream> payload){
        OrderStream orderStream  = payload.value();
        log.warn("Order Declined Topic Consumed", orderStream);
        propertyService.updateStatus(orderStream.getProperty().getPropertyId());

    }




}
