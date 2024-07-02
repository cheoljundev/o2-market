package com.o2.site.member.service;

import com.o2.site.member.dao.MemberDao;
import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.domain.Member;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberDao memberDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO UserId = memberDao.findById(username);
        if (UserId == null) {
            throw new UsernameNotFoundException("유저 정보가 없습니다!");
        }

        return new CustomUserDetails(UserId);
    }
}
