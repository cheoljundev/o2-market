package com.o2.site.member.main.dao;

import com.o2.site.member.main.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    // 회원 추가
    void insertMember(Member member);
}