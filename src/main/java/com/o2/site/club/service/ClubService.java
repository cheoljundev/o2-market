package com.o2.site.club.service;

import com.o2.site.club.dao.ClubMapper;
import com.o2.site.club.domain.RequestList;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ClubUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public Page<Map<String, Object>> getClubList(ClubDto clubDto, Pageable pageable) {
        System.out.println(pageable + "-------------------------");
        System.out.println(pageable.getOffset());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());


        RequestList<?> requestList = RequestList.builder()
                .data(clubDto)
                .pageable(pageable)
                .build();

        List<Map<String, Object>> content = clubMapper.clubList(requestList);
        int total = clubMapper.clubListCount(clubDto);

        return new PageImpl<>(content, pageable, total);
    }

}
