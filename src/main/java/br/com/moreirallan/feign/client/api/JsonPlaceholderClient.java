package br.com.moreirallan.feign.client.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceholderClient {

    @Autowired(required = false)
    @Qualifier("jsonPlaceholderAPI")
    JsonPlaceholderAPI jsonPlaceholderAPI;

    public JsonPlaceholderAPI getJsonPlaceholderAPI() {
        return jsonPlaceholderAPI;
    }
}
