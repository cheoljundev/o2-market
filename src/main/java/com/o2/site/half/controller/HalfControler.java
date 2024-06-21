package com.o2.site.half.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HalfControler {
    @GetMapping
    public String main() {
        return "main/main";
    }
}
