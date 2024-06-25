package com.o2.site.member.main.controller;

import com.o2.site.member.main.dto.MemberDTO;
import com.o2.site.member.main.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public String join(MemberDTO memberDTO) {
        memberService.registerMember(memberDTO);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public void login(MemberDTO memberDTO) {
        System.out.println("login 메서드 호출");

    }
}


