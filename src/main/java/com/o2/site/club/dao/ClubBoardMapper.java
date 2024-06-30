package com.o2.site.club.dao;

import com.o2.site.club.domain.RequestList;
import com.o2.site.club.dto.ClubBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClubBoardMapper {

    // 모임 내 게시판 리스트
    public List<Map<String, Object>> clubBoardList(RequestList<?> requestList);

    // 모임 내 게시판 총 카운트
    public int clubBoardListCount(ClubBoardDto clubBoardDto);

    // 모임 내 게시판 등록
    public int clubBoardInsert(ClubBoardDto clubBoardDto);

    // 모임 내 게시판 현재 seq 가져오기
    public int getClubBoardSeq();

    // 모임내 게시판 수정
    public int clubBoardUpdate();

    // 모임 내 게시판 이미지 삭제
    public int clubBoardImgDelete();


    List<Integer> getClubBoardId(String clubName);
}
