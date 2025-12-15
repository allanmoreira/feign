package br.com.moreirallan.feign.client.environments.api;

import feign.RequestLine;

public interface EnvironmentsAPI {

    @RequestLine("GET /teste")
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    Object getEnvironment();
}
