package com.o2.site.club.service;

import com.o2.site.club.dao.ClubScheduleMapper;
import com.o2.site.club.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubScheduleService {
    private final ClubScheduleMapper scheduleMapper;

    @Autowired
    public ClubScheduleService(ClubScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    public List<ScheduleDto> scheduleDetailList(String clubName) {
        System.out.println(clubName);
        return scheduleMapper.scheduleDetailList(clubName);
    }


    public int scheduleCreate(ScheduleDto scheduleDto) {
        return scheduleMapper.scheduleCreate(scheduleDto);
    }

    public ScheduleDto clubScheduleDeteil(long scheduleId) {
        return scheduleMapper.clubScheduleDeteil(scheduleId);
    }

    public List<ScheduleDto> scheduleList(String clubName) {
        return scheduleMapper.scheduleList(clubName);
    }

    public List<String> scheduleInUserList(long scheduleId) {
        return scheduleMapper.scheduleInUserList(scheduleId);
    }

    public int scheduleUserInCheck(ScheduleDto scheduleDto) {
        return scheduleMapper.scheduleUserInCheck(scheduleDto);
    }

    public int scheduleInUser(ScheduleDto scheduleDto) {
        return scheduleMapper.scheduleInUser(scheduleDto);
    }

    public int scheduleDelete(long scheduleId) {
        return scheduleMapper.scheduleDelete(scheduleId);
    }


}
