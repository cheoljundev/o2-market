package com.o2.site.club.service;

import com.o2.site.club.dao.ClubBoardMapper;
import com.o2.site.club.dao.ClubMapper;
import com.o2.site.club.dto.ClubBoardDto;
import com.o2.site.club.dto.ClubBoardImgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubBoardService {
    private final ClubBoardMapper clubBoardMapper;

    @Autowired
    public ClubBoardService(ClubBoardMapper clubBoardMapper) {
        this.clubBoardMapper = clubBoardMapper;
    }

    public int createClubBoard(ClubBoardDto clubBoardDto, String img) {

        String[] imgArr= img.split(",");

        int result = clubBoardMapper.clubBoardInsert(clubBoardDto);

        if (img.equals("") || img == null){

        } else {
            for (String imgOne : imgArr) {
                ClubBoardImgDto imgDto = new ClubBoardImgDto();
                imgDto.setBoardId(clubBoardDto.getClubBoardId());
                imgDto.setImage(imgOne);
                clubBoardMapper.clubBoardImgInsert(imgDto);
            }
        }



        return result;
    }

}
