package com.o2.site.member.controller;

import com.o2.site.member.dto.MemberDTO;
import com.o2.site.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    private MemberService memberService;

    @Autowired
    public JoinController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    @PostMapping("/join")
    public String joinP(HttpServletRequest request) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(request.getParameter("id"));
        if (request.getParameter("password").equals(request.getParameter("passwordCheck"))) {
            memberDTO.setPassword(request.getParameter("password"));
        } else
            return "member/join";
        memberDTO.setName(request.getParameter("name"));
        memberDTO.setPhoneNumber(request.getParameter("phoneNumber"));
        memberDTO.setAddress(request.getParameter("address"));
        System.out.println(memberDTO);

        memberService.registerMember(memberDTO);

        return "member/login";
    }
}