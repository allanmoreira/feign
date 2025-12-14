package br.com.moreirallan.feign.component;

import br.com.moreirallan.feign.feign.DefaultErrorDecoder;
import br.com.moreirallan.feign.properties.service.FeignConfig;
import br.com.moreirallan.feign.properties.service.ServiceItem;
import br.com.moreirallan.feign.properties.service.ServiceProperties;
import feign.form.FormEncoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfigurationResolver {

    @Autowired
    ServiceProperties serviceProperties;
    @Autowired
    FormEncoder formEncoder;
    @Autowired
    OptionalDecoder optionalDecoder;

    public ServiceItem getConfiguration(String serviceName){
        ServiceItem serviceItem = serviceProperties.getConfigurations().get(serviceName);
        if (serviceItem == null)
            throw new RuntimeException("Service configuration not found for: " + serviceName);

        serviceItem.setFeign(new FeignConfig(formEncoder, optionalDecoder, new DefaultErrorDecoder(serviceItem)));

        return serviceItem;
    }
}
