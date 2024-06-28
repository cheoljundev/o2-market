package com.o2.site.club.controller;

import com.o2.site.club.service.ClubBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club/schedule")
public class ClubScheduleContrlloer {

    @Autowired
    ClubBoardService clubBoardService;

    @GetMapping("/list")
    public void listGo() {}

    @GetMapping("/detail")
    public void detailGo() {}

    @GetMapping("/create")
    public void createGo() {}

}
