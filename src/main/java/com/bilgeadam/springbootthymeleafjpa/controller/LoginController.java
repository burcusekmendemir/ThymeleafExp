package com.bilgeadam.springbootthymeleafjpa.controller;

import com.bilgeadam.springbootthymeleafjpa.model.CustomUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "systemlogin")
public class LoginController {

    @GetMapping
    public ModelAndView systemlogin(){
        ModelAndView systemLoginView= new ModelAndView("systemlogin");
        systemLoginView.addObject("userinfo", new CustomUser());
        return systemLoginView;
    }
}
