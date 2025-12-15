package br.com.moreirallan.feign.controller;

import br.com.moreirallan.feign.domain.ResponseDTO;
import br.com.moreirallan.feign.service.JsonPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/jsonplaceholder")
public class JsonPlaceholderController {

    @Autowired
    JsonPlaceholderService jsonPlaceholderService;

    @GetMapping("users")
    public ResponseDTO<Object> users() {
        return new ResponseDTO<>(jsonPlaceholderService.users());
    }
}
