package edu.miu.property.kafka;

import edu.miu.property.dto.PropertyDto;
import edu.miu.property.dto.PropertyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

//    private KafkaTemplate<String,String> kafkaTemplate;
    private KafkaTemplate<String, PropertyDto> kafkaTemplate;

//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public KafkaProducer(KafkaTemplate<String, PropertyDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PropertyRequest request){
        log.info(String.format("Message sent "));
        Message<PropertyRequest> message = MessageBuilder
                .withPayload(request)
                        .setHeader(KafkaHeaders.TOPIC,"propertyTopic")
                                .build();
        kafkaTemplate.send( message);
    }
}
