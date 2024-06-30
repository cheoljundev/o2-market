package com.o2.site.club.service;

import com.o2.site.club.dao.ClubMapper;
import com.o2.site.club.dao.ClubScheduleMapper;
import com.o2.site.club.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubScheduleService {
    private final ClubScheduleMapper ScheduleMapper;

    @Autowired
    public ClubScheduleService(ClubScheduleMapper ScheduleMapper) {
        this.ScheduleMapper = ScheduleMapper;
    }

    public List<ScheduleDto> scheduleDetailList(String clubName) {
        System.out.println(clubName);
        return ScheduleMapper.scheduleDetailList(clubName);
    }



}
