package com.o2.site.member.domain;

import com.o2.site.member.dto.Authority;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long memberNo;
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String mileage;
    private List<Authority> authorities;
}
