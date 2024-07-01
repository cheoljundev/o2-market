package com.o2.site.club.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class ClubUserDto {

    private int userNo;
    private String clubName;
    private char inCk;
    private Date inDate;

}
