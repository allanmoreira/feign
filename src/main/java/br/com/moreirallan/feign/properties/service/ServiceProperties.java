package br.com.moreirallan.feign.properties.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("services")
public class ServiceProperties {

    protected final Map<String, ServiceItem> configurations = new HashMap<>();

    public Map<String, ServiceItem> getConfigurations() {
        return configurations;
    }
}
