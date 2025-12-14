package br.com.moreirallan.feign.feign;

import br.com.moreirallan.feign.utils.FeignUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Encoder;
import feign.optionals.OptionalDecoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class FeignConfig {

    @Bean("objectMapper")
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return FeignUtils.defaultMapper();
    }

    @Bean(name = "optionalDecoder")
    @Primary
    public OptionalDecoder optionalDecoder(@Qualifier("objectMapper") ObjectMapper objectMapper) {
        return new OptionalDecoder(new JacksonDecoder(objectMapper));
    }

    @Bean("formEncoder")
    @Primary
    public FormEncoder formEncoder(@Qualifier("objectMapper") ObjectMapper objectMapper) {
        return new FormEncoder(new JacksonEncoder(objectMapper));
    }

    @Bean(name = "defaultErrorDecoder")
    @Primary
    public DefaultErrorDecoder defaultErrorDecoder(@Qualifier("objectMapper") ObjectMapper objectMapper) {
        return new DefaultErrorDecoder();
    }

    @Bean("encoder")
    @Primary
    public Encoder feignFormEncoder (ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    @Bean(name = "multiPartDecoder")
    @Primary
    public MultiPartDecoder multiPartDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new MultiPartDecoder(messageConverters);
    }
}
