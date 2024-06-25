package com.o2.site.member.main.service;

import com.o2.site.member.main.dto.MemberDTO;

public interface MemberService {
    // 유저 가입시
    void registerMember(MemberDTO memberDTO);
}
