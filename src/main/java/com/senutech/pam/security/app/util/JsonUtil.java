package com.senutech.pam.security.app.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.time.Instant;

@JsonComponent
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public static class InstantSerializer extends JsonSerializer<Instant> {

        @Override
        public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class InstantDeserializer extends JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Instant.parse(p.getValueAsString());
        }
    }

    public static String objectToJson(Object obj) {
        String json = "Unable to serialize object";
        try {
            json = mapper.writeValueAsString(obj);
        } catch(Exception e) {
            logger.error("Error stringinizing object - " + e.getMessage(),e);
            e.printStackTrace(System.err);
        }
        return json;
    }

    public static String objectToPrettyJson(Object obj) {
        String json = "Unable to serialize object";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch(Exception e) {
            logger.error("Error stringinizing object - " + e.getMessage(),e);
            e.printStackTrace(System.err);
        }
        return json;
    }
}
