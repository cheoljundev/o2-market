package com.o2.site.member.domain;

import com.o2.site.member.dto.Authority;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Member {

    private Long memberNo;
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String image;
    private String mileage;
    private List<Authority> authorities;
}
