package com.o2.site.member.dao;

import com.o2.site.member.domain.Member;
import com.o2.site.member.domain.MemberRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void insertMember(Member member);

    Member findByUsername(String id);

    Member findByMemberNo(Long memberNo);

    Long findMemberNo(String id);

    void insertMemberRole(MemberRole memberRole);

    List<MemberRole> findMemberRole(Long memberNo);

    void updateMember(Member member);
}
