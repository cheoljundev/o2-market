package com.o2.site.member.main.service;

import com.o2.site.member.main.dao.MemberMapper;
import com.o2.site.member.main.domain.Member;
import com.o2.site.member.main.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class MemberServiceImpl implements MemberService {

    // 의존성 주입
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberMapper = memberMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    // DTO를 매개변수로 받아서 값 매핑
    // 단, 비밀번호는 두개가 같아야 설정됨.
    public void registerMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        if (memberDTO.getPassword().equals(memberDTO.getPasswordCheck())) {
            member.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        }
        member.setName(memberDTO.getName());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setAddress(memberDTO.getAddress());
        memberMapper.insertMember(member);
        System.out.println(member);
        System.out.println("가입 성공");
    }
}