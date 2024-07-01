package com.o2.site.club.dto;

import com.o2.site.club.domain.RequestList;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
