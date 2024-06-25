package com.o2.site.member.service;

import com.o2.site.member.dto.MemberDTO;

public interface MemberService {
    // 유저 가입시
    void registerMember(MemberDTO memberDTO);
}
