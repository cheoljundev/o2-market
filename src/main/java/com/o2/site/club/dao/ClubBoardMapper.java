package com.o2.site.club.dao;

import com.o2.site.club.dto.ClubBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ClubBoardMapper {

    // 모임 내 게시판 리스트
    public ArrayList<ClubBoardDto> clubBoardList();

    // 모임 내 게시판 총 카운트
    public int clubBoardListCount();

    // 모임 내 게시판 등록
    public int clubBoardInsert(ClubBoardDto clubBoardDto);


    // 모임내 게시판 수정
    public int clubBoardUpdate();

    // 모임 내 게시판 이미지 삭제
    public int clubBoardImgDelete();


    
}
