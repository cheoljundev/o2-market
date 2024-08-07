package com.o2.site.member.dto;

import com.o2.site.member.domain.MemberRole;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
        private Long memberNo;
        private String id;
        private String password;
        private String passwordCheck;
        private String name;
        private String phoneNumber;
        private String address;
        private String image;
        private int mileage;
        List<MemberRole> memberRoles;
}