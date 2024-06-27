package com.o2.site.club.controller;

import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.service.ClubService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.net.http.HttpRequest;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/club")
public class ClubContrlloer {

    @Autowired
    ClubService clubService;

    @GetMapping("/main")
    public void mainGo() {}

    @GetMapping("/create")
    public void createGo() {}

    @PostMapping("/createAction")
    public String createAction(ClubDto clubDto) {
        System.out.println(clubDto);
        return "redirect:main";
    }

    @GetMapping("/getList")
    @RequestBody
    public ResponseEntity<?> getListClub(ClubDto clubDto, @PageableDefault(size = 9) Pageable pageable) {
        return ResponseEntity.ok(clubService.getClubList(clubDto, pageable));
    }

}
