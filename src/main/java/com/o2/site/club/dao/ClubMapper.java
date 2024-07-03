package com.o2.site.club.dao;

import com.o2.site.club.domain.RequestList;
import com.o2.site.club.dto.ClubCategoryDto;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ClubUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClubMapper {

    // 모임 리스트
    public List<Map<String, Object>> clubList(RequestList<?> requestList);
    
    // 모임 리스트 총 카운트
    public int clubListCount(ClubDto clubDto);

    // 모임 생성
    public int clubInsert(ClubDto clubDto);

    // 모임 카테고리 리스트
    public ArrayList<ClubCategoryDto> clubCategoryList();

    // 모임 상세보기
    public ClubDto clubDeteil(String clubName);

    // 모임 참여
    public int clubUserInsert(ClubUserDto clubUserDto);

// 관리자

    // 모임원 탈퇴
    public int clubUserDelete(ClubUserDto clubUserDto);

    // 신청원 리스트
    public ArrayList<ClubUserDto> clubRequestUserList();

    // 신청원 수락
    public int clubUserIn(ClubUserDto clubUserDto);

    // 모임원 관리 요청 리스트
    public List<ClubUserDto> clubAppUserList(ClubUserDto clubUserDto);

    // 모임원 관리 모임원 리스트
    public List<ClubUserDto> clubUserList(ClubUserDto clubUserDto);
    
    // 모임원인지 체크
    public int clubUserInCheck(ClubUserDto clubUserDto);

    // 모임원인지 체크
    public int clubAppUserCheck(ClubUserDto clubUserDto);

// 관리자 end

    
}
