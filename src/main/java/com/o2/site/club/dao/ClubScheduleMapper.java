package com.o2.site.club.dao;

import com.o2.site.club.dto.ScheduleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ClubScheduleMapper {

    // 일정 리스트
    public ArrayList<ScheduleDto> scheduleDetailList(String clubName);
    
    // 일정 수정
    public int scheduleUpdate();

    // 일정 삭제
    public int scheduleDelete();

    // 일정 상세보기
    public List<ScheduleDto> scheduleDetail();
    
    // 일정 생성
    int scheduleCreate(ScheduleDto scheduleDto);

    ScheduleDto clubScheduleDeteil(long scheduleId);
}
