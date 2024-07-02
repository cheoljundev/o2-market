package com.o2.site.member.controller;

import com.o2.site.member.domain.Member;
import com.o2.site.member.domain.MemberRole;
import com.o2.site.member.dto.MemberDTO;
import com.o2.site.member.dto.MemberJoinDTO;
import com.o2.site.member.service.MemberService;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UploadService uploadService;


    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(MemberJoinDTO memberJoinDTO, Model model) throws IOException {
        if (!memberJoinDTO.getPassword().equals(memberJoinDTO.getPasswordCheck())) {
            model.addAttribute("비밀번호가 일치하지 않습니다.");
            return "/member/join";
        }
        // 비밀번호 암호화
        String encodePassword = bCryptPasswordEncoder.encode(memberJoinDTO.getPassword());

        Member member = Member.builder()
                .id(memberJoinDTO.getId())
                .password(encodePassword)
                .name(memberJoinDTO.getName())
                .phoneNumber(memberJoinDTO.getPhoneNumber())
                .address(memberJoinDTO.getAddress())
                .mileage(0)
                .build();


        memberService.join(member);
        Long memberNo = memberService.findMemberNo(member.getId());

        uploadService.insertImage(UploadImageDto.builder()
                .memberNo(memberNo)
                .image(memberJoinDTO.getImage())
                .build());


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