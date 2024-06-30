package com.o2.site.club.service;

import com.o2.site.club.dao.ClubBoardMapper;
import com.o2.site.club.domain.RequestList;
import com.o2.site.club.dto.ClubBoardDto;
import com.o2.site.club.dto.ClubDto;
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
public class ClubBoardService {
    private final ClubBoardMapper clubBoardMapper;

    @Autowired
    public ClubBoardService(ClubBoardMapper clubBoardMapper) {
        this.clubBoardMapper = clubBoardMapper;
    }

    public Page<Map<String, Object>> getClubBoardList(ClubBoardDto clubBoardDto, Pageable pageable) {

        RequestList<?> requestList = RequestList.builder()
                .data(clubBoardDto)
                .pageable(pageable)
                .build();

        List<Map<String, Object>> content = clubBoardMapper.clubBoardList(requestList);
        int total = clubBoardMapper.clubBoardListCount(clubBoardDto);

        return new PageImpl<>(content, pageable, total);
    }

    public int createClubBoard(ClubBoardDto clubBoardDto) {

        int result = clubBoardMapper.clubBoardInsert(clubBoardDto);

        return result;
    }

    public int getClubBoardSeq() {
        return clubBoardMapper.getClubBoardSeq();
    }

    public List<Integer> getClubBoardId(String clubName) {

        return clubBoardMapper.getClubBoardId(clubName);
    }

    public ClubBoardDto clubBoardDeteil(long clubBoardId) {

        return clubBoardMapper.clubBoardDeteil(clubBoardId);
    }
}
