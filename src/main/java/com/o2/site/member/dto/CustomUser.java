package com.o2.site.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUser extends User {
    private final String role;
    public CustomUser(MemberDTO memberDTO, Collection<? extends GrantedAuthority> authorities) {
        super(memberDTO.getId(), memberDTO.getPassword(), authorities);
        this.role = memberDTO.getUserRole();
    }
}