package com.o2.site.club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
public class ClubContrlloer {

    @RequestMapping("/main")
    public void mainGo() {}

}
