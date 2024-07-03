package com.o2.site.club.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class ClubDto {

    private String clubName;
    private long readerNo;
    private String categoryId;
    private int membersLimit;
    private int ageLimitStart;
    private int ageLimitEnd;
    private String area;
    private String oneLineIntro;
    private String intro;
    private Date createDate;
    private String imageStr;
    private String categoryName;
    private int membersCount;

}
