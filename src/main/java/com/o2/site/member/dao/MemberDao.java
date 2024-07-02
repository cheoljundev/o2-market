package com.o2.site.member.dao;

import com.o2.site.member.domain.MemberRole;
import com.o2.site.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final MemberMapper memberMapper;

    public MemberDTO findById(String id){
        Long memberNo = memberMapper.findByUsername(id).getMemberNo();
        List<MemberRole> memberRoles =findMemberRole(memberNo);
        return MemberDTO.builder()
                .id(id)
                .password(memberMapper.findByUsername(id).getPassword())
                .name(memberMapper.findByUsername(id).getName())
                .phoneNumber(memberMapper.findByUsername(id).getPhoneNumber())
                .address(memberMapper.findByUsername(id).getAddress())
                .mileage(0)
                .memberRoles(memberRoles)
                .build();
    }

    public List<MemberRole> findMemberRole(Long memberNo){
        return memberMapper.findMemberRole(memberNo);
    }
}
