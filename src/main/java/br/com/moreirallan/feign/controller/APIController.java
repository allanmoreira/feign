package br.com.moreirallan.feign.controller;

import br.com.moreirallan.feign.domain.ResponseDTO;
import br.com.moreirallan.feign.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "api")
public class APIController {

    @Autowired
    JsonUtils jsonUtils;

    @GetMapping("dev/teste")
    public ResponseDTO<Object> dev() throws IOException {
        return new ResponseDTO<>(jsonUtils.deserialize("{\"env\":\"development\"}", Object.class));
    }

    @GetMapping("prd/teste")
    public ResponseDTO<Object> prd() throws IOException {
        return new ResponseDTO<>(jsonUtils.deserialize("{\"prd\":\"production\"}", Object.class));
    }
}
