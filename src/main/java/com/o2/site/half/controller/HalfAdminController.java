package com.o2.site.half.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/half")
public class HalfAdminController {

    @GetMapping
    public String index() {
        return "/half/admin/order";
    }

    @GetMapping("/order")
    public String order() {
        return "/half/admin/order";
    }
}
