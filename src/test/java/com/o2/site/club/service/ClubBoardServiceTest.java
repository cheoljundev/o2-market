package com.o2.site.club.service;

import com.o2.site.club.dto.ClubBoardDto;
import com.o2.site.config.MybatisConfig;
import com.o2.site.config.O2Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional // 테스트케이스에선 테스트만하고 사라짐
class ClubBoardServiceTest {

    @Autowired
    ClubBoardService clubBoardService;


    @Test
    void createClubBoard() {
        for (int i = 2 ; i <= 222; i++) {
            ClubBoardDto clubBoardDto = new ClubBoardDto();
            clubBoardDto.setClubName("12ㅌㅊ4");
            clubBoardDto.setTitle("즐거운 여행");
            clubBoardDto.setWriter(1);
            clubBoardDto.setContent("즐거운 하루를 보낸 내용이 가득한 내용1");
            clubBoardService.createClubBoard(clubBoardDto);
        }
    }
}