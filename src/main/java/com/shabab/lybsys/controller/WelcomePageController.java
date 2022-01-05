package com.shabab.lybsys.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/home")
@RestController
@Api(value = "Home Page", description = "Welcome to LybSys Application")
public class WelcomePageController {

    @GetMapping
    public String welcomePage(){
        return "Welcome to LybSys Application";
    }
}
