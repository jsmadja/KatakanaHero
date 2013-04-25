package com.jsmadja.katakanahero.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_SETTERS;

@Provider
public class JacksonMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public JacksonMapperProvider() {
        mapper = createMapper();
    }

    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(AUTO_DETECT_SETTERS);
        mapper.disable(AUTO_DETECT_GETTERS);
        mapper.setSerializationInclusion(NON_NULL);
        VisibilityChecker<?> checker = mapper.getSerializationConfig().getDefaultVisibilityChecker();
        mapper.setVisibilityChecker(checker.withFieldVisibility(ANY));
        return mapper;
    }

}
