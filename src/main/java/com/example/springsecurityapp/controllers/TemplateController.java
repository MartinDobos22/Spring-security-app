package com.example.springsecurityapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TemplateController {

    @GetMapping(path = "login")
    public String getLoginView(){
        return "login";
    }


    @GetMapping(path = "courses")
    public String getCoursesView(){
        return "courses";
    }

}
