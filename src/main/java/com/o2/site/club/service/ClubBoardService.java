package com.o2.site.club.service;

import com.o2.site.club.dao.ClubBoardMapper;
import com.o2.site.club.dto.ClubBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubBoardService {
    private final ClubBoardMapper clubBoardMapper;

    @Autowired
    public ClubBoardService(ClubBoardMapper clubBoardMapper) {
        this.clubBoardMapper = clubBoardMapper;
    }

    public int createClubBoard(ClubBoardDto clubBoardDto, String img) {
        
        int result = clubBoardMapper.clubBoardInsert(clubBoardDto);

        return result;
    }

}
