package com.o2.site.club.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ClubBoardDto {
    private long clubBoardId;
    private String clubName;
    private long writer;
    private String title;
    private String content;
    private Date createDate;
}
