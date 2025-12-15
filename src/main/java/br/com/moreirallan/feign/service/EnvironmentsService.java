package br.com.moreirallan.feign.service;

import br.com.moreirallan.feign.client.environments.api.EnvironmentsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentsService {

    @Autowired
    EnvironmentsClient environmentsClient;

    public Object get(String environment) {
        return environmentsClient.getEnvironmentsAPI(environment).getEnvironment();
    }
}
