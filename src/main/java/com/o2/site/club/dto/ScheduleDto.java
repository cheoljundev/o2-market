package com.o2.site.club.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ScheduleDto {
    private long scheduleId;
    private String clubName;
    private Date dateTime;
    private String address;
    private Date createDate;
}
