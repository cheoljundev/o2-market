package com.o2.site.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {
    @GetMapping
    public String main() {
        return "main/main";
    }
}
