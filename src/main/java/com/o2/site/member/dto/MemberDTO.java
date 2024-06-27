package com.o2.site.member.dto;

import lombok.*;

// HTML 폼에서 받은 데이터를 담는 DTO 클래스 (단순 전달용)

@Getter
@Setter
@Data
public class MemberDTO {
        private String id;
        private String password;
        private String passwordCheck; // 가입할 때 패스워드 확인용
        private String name;
        private String phoneNumber;
        private String address;
        private String image;
        private int mileage;
        private String userRole;
}