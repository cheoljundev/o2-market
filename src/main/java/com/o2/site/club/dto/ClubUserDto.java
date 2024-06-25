package com.o2.site.club.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
