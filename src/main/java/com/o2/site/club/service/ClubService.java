package com.o2.site.club.service;

import com.o2.site.club.dao.ClubMapper;
import com.o2.site.club.dto.ClubBoardDto;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ClubUserDto;
import com.o2.site.trade.dao.TradeMapper;
import com.o2.site.trade.dto.ApplicationDto;
import com.o2.site.trade.dto.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubService {
    private final ClubMapper clubMapper;

    @Autowired
    public ClubService(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public int createClub(ClubDto clubDto) {

        ClubUserDto clubUserDto = new ClubUserDto();

        int result = clubMapper.clubInsert(clubDto);

//        clubMapper.clubUserInsert(clubUserDto);

        return result;
    }


}
