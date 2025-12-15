package br.com.moreirallan.feign.client.environments.api;

import br.com.moreirallan.feign.component.ServiceConfigurationResolver;
import br.com.moreirallan.feign.feign.FeignClientFactory;
import br.com.moreirallan.feign.properties.service.ServiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentsClient {

    @Autowired
    ServiceConfigurationResolver serviceConfigurationResolver;

    public EnvironmentsAPI getEnvironmentsAPI(String environment) {
        ServiceItem service = serviceConfigurationResolver.getConfiguration("environments", environment);
        return new FeignClientFactory(service)
                .create(EnvironmentsAPI .class);
    }
}
