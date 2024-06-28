package com.o2.site.member.service;

import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Autowired
    public UserDetailService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.findByUsername(username);
        if (memberDTO == null) {
            throw new UsernameNotFoundException("유저 정보가 없습니다!");
        }
        List<GrantedAuthority> authorities = memberDTO.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        return new User(memberDTO.getId(), memberDTO.getPassword(), authorities);
    }
}
