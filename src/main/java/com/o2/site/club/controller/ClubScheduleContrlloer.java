package com.o2.site.club.controller;

import com.o2.site.club.dto.ScheduleDto;
import com.o2.site.club.service.ClubBoardService;
import com.o2.site.club.service.ClubScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/club/schedule")
public class ClubScheduleContrlloer {

    @Autowired
    ClubScheduleService clubScheduleService;

    @GetMapping("/list")
    public void listGo() {}

    @GetMapping("/getList")
    @ResponseBody
    public List<ScheduleDto> getList(@RequestParam("clubName")String clubName) {
        List<ScheduleDto> scheduleList = clubScheduleService.scheduleList(clubName);
        return scheduleList;
    }

    @GetMapping("/detail")
    public void detailGo(@RequestParam("scheduleId") long scheduleId, Model model) {
        ScheduleDto scheduleDto = clubScheduleService.clubScheduleDeteil(scheduleId);
        System.out.println(scheduleDto);
        model.addAttribute("scheduleDto", scheduleDto);
    }

    @GetMapping("/create")
    public void createGo() {}

    @PostMapping("/create")
    public void createAction(ScheduleDto scheduleDto) {
        System.out.println(scheduleDto);
        clubScheduleService.scheduleCreate(scheduleDto);
    }
}
