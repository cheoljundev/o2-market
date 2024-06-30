package com.o2.site.member.controller;

import com.o2.site.member.domain.Member;
import com.o2.site.member.domain.MemberRole;
import com.o2.site.member.dto.MemberDTO;
import com.o2.site.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(MemberDTO memberDTO, Model model) {
        if (!memberDTO.getPassword().equals(memberDTO.getPasswordCheck())) {
            model.addAttribute("비밀번호가 일치하지 않습니다.");

            return "/member/join";
        }
        // 비밀번호 암호화
        String encodePassword = bCryptPasswordEncoder.encode(memberDTO.getPassword());

        Member member = new Member();

        member.setId(memberDTO.getId());
        member.setPassword(encodePassword);
        member.setName(memberDTO.getName());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setAddress(memberDTO.getAddress());

        System.out.println(memberDTO);
        System.out.println(member);
        memberService.join(member);

        // 번호 뽑아짐
        Long memberNo = memberService.findMemberNo(memberDTO.getId());
        System.out.println(memberNo);

        if (memberNo != null) {
            MemberRole memberRole = new MemberRole(memberNo, "ROLE_USER");
            memberService.insertRole(memberRole);
        }
        
        return "member/login";
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }
}