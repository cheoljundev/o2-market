package com.o2.site.half.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/half")
public class HalfUserController {
    @GetMapping("/list")
    public String main() {
        return "half/user/list";
    }

    @GetMapping("/detail/{id}")
    public String detail() {
        return "half/user/detail";
    }

    @GetMapping("/order/{id}")
    public String order() {
        return "half/user/order";
    }
}
