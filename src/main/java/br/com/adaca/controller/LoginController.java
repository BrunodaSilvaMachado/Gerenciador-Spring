package br.com.adaca.controller;

import br.com.adaca.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LoginController {

    @Autowired
    private AdministradorService loginService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/Gerenciador/login");
    }

    @GetMapping("/Gerenciador/login")
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }

    @GetMapping("/Gerenciador/menu")
    ModelAndView accessMenu() {
        return new ModelAndView("menu");
    }

    @GetMapping("/access-denied")
    ModelAndView acessDenied() {
        return new ModelAndView("error/403");
    }
}
