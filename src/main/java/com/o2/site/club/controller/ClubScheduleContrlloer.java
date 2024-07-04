package com.o2.site.club.controller;

import com.o2.site.club.function.ClubFunction;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ClubUserDto;
import com.o2.site.club.dto.ScheduleDto;
import com.o2.site.club.service.ClubScheduleService;
import com.o2.site.club.service.ClubService;
import com.o2.site.member.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/club/schedule")
public class ClubScheduleContrlloer {

    @Autowired
    ClubScheduleService clubScheduleService;

    @Autowired
    ClubService clubService;

    @GetMapping("/list")
    public void listGo(@AuthenticationPrincipal CustomUserDetails user, Model model, @RequestParam("clubName") String clubName) {
        long loginUserNo = ClubFunction.getUserNo(user, model);
        ClubDto clubDto = clubService.getClubInfo(clubName);

        model.addAttribute("clubDto", clubDto);
    }

    @GetMapping("/getList")
    @ResponseBody
    public List<ScheduleDto> getList(@RequestParam("clubName")String clubName) {
        List<ScheduleDto> scheduleList = clubScheduleService.scheduleList(clubName);
        return scheduleList;
    }

    @GetMapping("/detail")
    public void detailGo(@RequestParam("scheduleId") long scheduleId, Model model, @AuthenticationPrincipal CustomUserDetails user, @RequestParam("clubName") String clubName) {
        ScheduleDto scheduleDto = clubScheduleService.clubScheduleDeteil(scheduleId);
        System.out.println(scheduleDto);
        long loginUserNo = ClubFunction.getUserNo(user, model);
        ClubUserDto clubUserDto = new ClubUserDto();
        clubUserDto.setUserNo(loginUserNo);
        clubUserDto.setClubName(clubName);
        scheduleDto.setUserNo(loginUserNo);
        List<String> userList = clubScheduleService.scheduleInUserList(scheduleId);
        int userInCheck = clubService.clubUserInCheck(clubUserDto);
        int appUserCheck = clubService.clubAppUserCheck(clubUserDto);
        int scheduleUserInCheck = clubScheduleService.scheduleUserInCheck(scheduleDto);
        ClubDto clubDto = clubService.getClubInfo(clubName);

        model.addAttribute("scheduleDto", scheduleDto);
        model.addAttribute("userList", userList);
        model.addAttribute("userInCheck", userInCheck);
        model.addAttribute("appUserCheck", appUserCheck);
        model.addAttribute("scheduleUserInCheck", scheduleUserInCheck);
        model.addAttribute("clubDto", clubDto);

    }

    @GetMapping("/create")
    public void createGo() {}

    @PostMapping("/create")
    public void createAction(ScheduleDto scheduleDto) {
        System.out.println(scheduleDto);
        clubScheduleService.scheduleCreate(scheduleDto);
    }

    @PostMapping("/scheduleInUser")
    @ResponseBody
    public void scheduleInUser(ScheduleDto scheduleDto) {
        clubScheduleService.scheduleInUser(scheduleDto);
    }

    @PostMapping("/delete")
    @ResponseBody
    public int delete(@RequestParam("scheduleId") long scheduleId) {
        return clubScheduleService.scheduleDelete(scheduleId);
    }
}
