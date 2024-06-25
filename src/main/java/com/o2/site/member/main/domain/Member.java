package com.o2.site.member.main.domain;

import lombok.*;

// db, 비즈니스 로직에 이용하기 위한 객체 (엔티티)
// DTO에서 받은 데이터를 실제 데이터베이스에 매핑할 Entity 클래스

@Getter
@Setter
@ToString
public class Member {
    private Long memberNo; // 데이터베이스에서 자동 생성되는 PK 필드
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String image;
    private int mileage;
}