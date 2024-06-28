package com.o2.site.member.dao;

import com.o2.site.member.domain.Member;
import com.o2.site.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void insertMember(Member member);

    MemberDTO findByUsername(String id);

}
