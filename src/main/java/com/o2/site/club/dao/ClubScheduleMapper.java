package com.o2.site.club.dao;

import com.o2.site.club.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ClubScheduleMapper {

    // 일정 리스트
    public ArrayList<ScheduleDto> scheduleList();

    // 일정 생성
    public int scheduleInsert();
    
    // 일정 수정
    public int scheduleUpdate();

    // 일정 삭제
    public int scheduleDelete();

}
