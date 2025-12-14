package br.com.moreirallan.feign.service;

import br.com.moreirallan.feign.client.api.JsonPlaceholderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonPlaceholderService {

    @Autowired
    JsonPlaceholderClient jsonPlaceholderClient;

    public Object users() {
        return jsonPlaceholderClient.getJsonPlaceholderAPI().users();
    }
}
