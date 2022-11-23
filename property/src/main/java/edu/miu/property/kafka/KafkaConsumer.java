package edu.miu.property.kafka;

import edu.miu.property.dto.PropertyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

//    @KafkaListener(topics = "propertyTopic", groupId = "myGroup")
//    public void consume(String message){
//        log.info("message is consumed " + message);
//    }

    @KafkaListener(topics = "propertyTopic", groupId = "myGroup")
    public void consume(PropertyRequest request){
        log.info("message is consumed " );
    }
}
