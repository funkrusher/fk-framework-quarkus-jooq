package org.fk.core.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.time.LocalDateTime;

@ApplicationScoped
public class ObjectMapperProducer {

    @Produces
    public ObjectMapper configureObjectMapper() {
        return ObjectMapperProducer.create();
    }

    public static ObjectMapper create() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        // UTC milliseconds since 1970 Serialize/Deserialize
        objectMapper.registerModule(new JavaTimeModule());

        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

        objectMapper.registerModule(module);
        return objectMapper;
    }
}