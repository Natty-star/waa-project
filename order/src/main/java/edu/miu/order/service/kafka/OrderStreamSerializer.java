package edu.miu.order.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;


public class OrderStreamSerializer implements Serializer<OrderStream> {

    public static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();


    @Override
    public byte[] serialize(String topic, OrderStream message)  {
        try {
            return mapper.writeValueAsBytes(message);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }
}
