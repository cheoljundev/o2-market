package com.o2.site.member.main.service.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {

    @GetMapping
    public String main() {
        return "main/main";
    }

    @GetMapping("/join")
    public String join() {
        return "main/join";
    }
}
