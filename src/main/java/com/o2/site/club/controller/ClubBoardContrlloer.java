package com.o2.site.club.controller;

import com.o2.site.club.dto.ClubCategoryDto;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.service.ClubBoardService;
import com.o2.site.club.service.ClubService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/club/board")
public class ClubBoardContrlloer {

    @Autowired
    ClubBoardService clubBoardService;

    @GetMapping("/list")
    public void listGo() {}

    @GetMapping("/detail")
    public void detailGo() {}

}
