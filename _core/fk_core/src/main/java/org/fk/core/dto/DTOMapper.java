package org.fk.core.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Help serializing a pojo, without the hassle of the JsonIgnore-Annotations that would normally make problems
 * with the unit-test and lead to wrong json, that always contains the full pojo, and not only the allowed stuff.
 * <p>
 * We use the bookKeepingMap of the pojos recursively and only use this, as the source of our data that we serialize,
 * as it contains only the set data of the pojo and not the data that has never been set.
 */
@ApplicationScoped
public class DTOMapper {

    @Inject
    ObjectMapper objectMapper;

    public <T> T deserializePojo(String value, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(value, valueType);
    }

    public String serializePojo(DTO pojo) throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator gen = objectMapper.getFactory().createGenerator(writer);
        serializePojo(pojo, gen, objectMapper);
        gen.flush();
        return writer.toString();
    }

    private void serializePojo(DTO value, JsonGenerator gen, ObjectMapper mapper) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }
        gen.writeStartObject();
        for (Map.Entry<String, Object> entry : value.getBookKeeper().touched().entrySet()) {
            gen.writeFieldName(entry.getKey());
            if (entry.getValue() instanceof DTO dto) {
                serializePojo(dto, gen, mapper);
            } else {
                serializeValue(entry.getValue(), gen, mapper);
            }
        }
        gen.writeEndObject();
    }

    private void serializeValue(Object value, JsonGenerator gen, ObjectMapper mapper) throws IOException {
        if (value instanceof Map) {
            gen.writeStartObject();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                gen.writeFieldName(entry.getKey().toString());
                if (entry.getValue() instanceof DTO) {
                    serializePojo((DTO) entry.getValue(), gen, mapper);
                } else {
                    serializeValue(entry.getValue(), gen, mapper);
                }
            }
            gen.writeEndObject();
        } else if (value instanceof List) {
            gen.writeStartArray();
            for (Object item : (List<?>) value) {
                if (item instanceof DTO dto) {
                    serializePojo(dto, gen, mapper);
                } else {
                    serializeValue(item, gen, mapper);
                }
            }
            gen.writeEndArray();
        } else if (value instanceof Iterable) {
            gen.writeStartArray();
            for (Object item : (Iterable<?>) value) {
                if (item instanceof DTO dto) {
                    serializePojo(dto, gen, mapper);
                } else {
                    serializeValue(item, gen, mapper);
                }
            }
            gen.writeEndArray();
        } else {
            mapper.writeValue(gen, value);
        }
    }
}