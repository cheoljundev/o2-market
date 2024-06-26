package com.o2.site.club.dto;

import com.o2.site.club.domain.RequestList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private String thumbnail;
    private Date createDate;

}
