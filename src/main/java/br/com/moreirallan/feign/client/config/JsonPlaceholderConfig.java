package br.com.moreirallan.feign.client.config;

import br.com.moreirallan.feign.client.api.JsonPlaceholderAPI;
import br.com.moreirallan.feign.component.ServiceConfigurationResolver;
import br.com.moreirallan.feign.feign.FeignClientFactory;
import br.com.moreirallan.feign.properties.service.ServiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonPlaceholderConfig {

    @Autowired
    ServiceConfigurationResolver serviceConfigurationResolver;

    @Bean("jsonPlaceholderAPI")
    @ConditionalOnProperty(name = "services.configurations.jsonplaceholder")
    public JsonPlaceholderAPI jsonPlaceholderAPI() {
        ServiceItem service = serviceConfigurationResolver.getConfiguration("jsonplaceholder");
        return new FeignClientFactory(service)
                .create(JsonPlaceholderAPI.class);
    }
}
