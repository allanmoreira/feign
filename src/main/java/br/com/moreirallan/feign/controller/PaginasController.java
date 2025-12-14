package br.com.moreirallan.feign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {

    @GetMapping("/resultados")
    public String resultados(Model model) {
        return "resultados";
    }

    @GetMapping("/usuario")
    public String usuario(Model model) {
        return "usuario";
    }


}
