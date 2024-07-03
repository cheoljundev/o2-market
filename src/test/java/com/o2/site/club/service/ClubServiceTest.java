package com.o2.site.club.service;

import com.o2.site.club.dto.ClubDto;
import com.o2.site.config.MybatisConfig;
import com.o2.site.config.O2Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class ClubServiceTest {

    @Autowired
    ClubService clubService;

    // 클럽 생성 테스트 (임의 데이터 넣기)
    @Test
    void createClub() {

        ClubDto clubDto = new ClubDto();
        for (int i = 2 ; i <= 222; i++) {

            clubDto.setClubName("모임"+i);
            clubDto.setReaderNo(1);
            clubDto.setCategoryId("CCI-001");
            clubDto.setMembersLimit(30);
            clubDto.setAgeLimitStart(20);
            clubDto.setAgeLimitEnd(30);
            clubDto.setArea("울산");
            clubDto.setOneLineIntro("울산 스포츠 모임입니다."+i);
            clubDto.setIntro("소개 글 입니다 "+i);
            clubService.createClub(clubDto);
        }

    }
}