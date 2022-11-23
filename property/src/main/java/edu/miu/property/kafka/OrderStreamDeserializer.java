package edu.miu.property.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderStreamDeserializer implements Deserializer<OrderStream> {
    public static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Override
    public OrderStream deserialize(String s, byte[] data) {
        try {
            return mapper.readValue(data, OrderStream.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
