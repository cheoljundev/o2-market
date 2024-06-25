package com.o2.site.club.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/club")
public class ClubContrlloer {

    @RequestMapping("/main")
    public void mainGo(HttpServletRequest request) throws IOException {

    }

}
