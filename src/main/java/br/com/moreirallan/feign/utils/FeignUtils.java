package br.com.moreirallan.feign.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FeignUtils.class);

    public static ObjectMapper defaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.enable(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature());
        mapper.enable(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS.mappedFeature());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JodaModule module = new JodaModule();
        mapper.registerModule(module);
        SimpleModule spModule = new SimpleModule();
        mapper.registerModule(spModule);
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static String serializeBaseResponse(Response response) {
        StringBuilder build = new StringBuilder();
        build.append("\nstatus: ").append(response.status());
        if (response.request() != null) {
            build.append(" method: ").append(response.request().httpMethod());
            build.append(" url: ").append(response.request().url()).append("\n");
            if (response.request().headers() != null && !response.request().headers().isEmpty()) {
                build.append("request-headers: {\n");
                response.request().headers().entrySet().forEach((entry) -> {
                    build.append("\t").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                });
                build.append("}\n");
            }
        }
        if (response.headers() != null && !response.headers().isEmpty()) {
            build.append("response-headers: {\n");
            response.headers().entrySet().forEach((entry) -> {
                build.append("\t").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            });
            build.append("}\n");
        }
        return build.toString();
    }

    public static void close(Response response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (Throwable throwable) {
            LOG.debug("Falha ao fechar resposta.", throwable);
        }
    }
}
