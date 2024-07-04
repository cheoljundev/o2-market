package com.o2.site.club.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class ScheduleDto {
    private long scheduleId;
    private long userId;
    private long userNo;
    private String clubName;
    private Date dateTime;
    private String address;
    private String addressDetail;
    private Date createDate;
}
