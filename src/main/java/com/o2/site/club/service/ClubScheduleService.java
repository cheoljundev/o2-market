package com.o2.site.club.service;

import com.o2.site.club.dao.ClubMapper;
import com.o2.site.club.dao.ClubScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubScheduleService {
    private final ClubScheduleMapper clubScheduleMapper;

    @Autowired
    public ClubScheduleService(ClubScheduleMapper clubScheduleMapper) {
        this.clubScheduleMapper = clubScheduleMapper;
    }



}
