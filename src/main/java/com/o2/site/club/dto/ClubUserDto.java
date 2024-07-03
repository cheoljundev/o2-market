package com.o2.site.club.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class ClubUserDto {

    private long userNo;
    private String clubName;
    private String id;
    private char inCk;
    private Date inDate;

}
