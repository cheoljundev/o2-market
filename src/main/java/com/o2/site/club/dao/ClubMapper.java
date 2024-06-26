package com.o2.site.club.dao;

import com.o2.site.club.domain.RequestList;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ClubUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

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

    // 모임 수정
    public int clubUpdate();

    // 모임 상세보기
    public ClubDto clubDeteil();

    // 모임 참여
    public ClubUserDto clubUserInsert(ClubUserDto clubUserDto);

// 관리자
    // 모임원 리스트
    public ArrayList<ClubUserDto> clubUserList();

    // 모임원 탈퇴
    public int clubUserDelete();

    // 신청원 리스트
    public ArrayList<ClubUserDto> clubRequestUserList();

    // 신청원 수락
    public int clubUserIn();

    // 신청원 취소
    public int clubUserCancle();


// 관리자 end

    
}
