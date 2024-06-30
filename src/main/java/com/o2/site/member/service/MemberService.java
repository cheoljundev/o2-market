package com.o2.site.member.service;

import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.domain.Member;
import com.o2.site.member.domain.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void join(Member member) {
        memberMapper.insertMember(member);
    }

    public void insertRole(MemberRole memberRole) {
        memberMapper.insertMemberRole(memberRole);
    }

    public Long findMemberNo(String id) {
        return memberMapper.findMemberNo(id);
    }
}
