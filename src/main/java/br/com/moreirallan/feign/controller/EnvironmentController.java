package br.com.moreirallan.feign.controller;

import br.com.moreirallan.feign.domain.ResponseDTO;
import br.com.moreirallan.feign.service.EnvironmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/environment")
public class EnvironmentController {

    @Autowired
    EnvironmentsService environmentsService;

    @GetMapping
    public ResponseDTO<Object> dev(@RequestParam String env) {
        return new ResponseDTO<>(environmentsService.get(env));
    }
}
