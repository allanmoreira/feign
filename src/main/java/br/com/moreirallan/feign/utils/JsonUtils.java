/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moreirallan.feign.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jc62053
 */
@Component
public class JsonUtils {

    @Autowired
    protected ObjectMapper objectMapper;

    public String serialize(Object obj, boolean pretty) throws JsonProcessingException {
        if (pretty)
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        return objectMapper.writeValueAsString(obj);
    }

    public String serializeIgnoringErrors(Object obj, boolean pretty) throws JsonProcessingException {
        if (pretty)
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T deserialize(String json, Class<T> type) throws IOException {
        return objectMapper.readValue(json, type);
    }

    public <T> T deserialize(InputStream json, Class<T> type)  throws IOException {
        return objectMapper.readValue(json, type);
    }

    public <T> T deserialize(String json, JavaType type)  throws IOException {
        return objectMapper.readValue(json, type);
    }

    public <T> T deserializeToCollection(String json, Class<?> type, Class<?> collection) throws IOException {
        return deserialize(json, getTypeFactory().constructParametricType(collection, type));
    }

    public TypeFactory getTypeFactory() {
        return objectMapper.getTypeFactory();
    }

}
