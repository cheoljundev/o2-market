package com.o2.site.member.dao;

import com.o2.site.member.domain.Member;
import com.o2.site.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

// jpa 랑 같은 역할

@Mapper
public interface MemberMapper {

    void findById(String id) {
    }

    // 회원 추가
    void insertMember(Member member);
}