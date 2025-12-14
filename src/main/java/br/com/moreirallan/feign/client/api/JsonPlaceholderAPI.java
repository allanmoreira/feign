package br.com.moreirallan.feign.client.api;

import feign.RequestLine;

public interface JsonPlaceholderAPI {

    @RequestLine("GET /users")
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    Object users();
}
