package com.o2.site.club.service;

import com.o2.site.club.dao.ClubMapper;
import com.o2.site.club.dto.ClubCategoryDto;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ClubUserDto;
import com.o2.site.club.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClubService {
    private final ClubMapper clubMapper;

    @Autowired
    public ClubService(ClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    public int createClub(ClubDto clubDto) {
        int result = clubMapper.clubInsert(clubDto);
        return result;
    }

    public List<ClubDto> getClubList(PageDto pageDto) {
        System.out.println(pageDto);
        return clubMapper.clubList(pageDto);
    }

    public int clubListCount(PageDto pageDto) {
        return clubMapper.clubListCount(pageDto);
    }


    public ArrayList<ClubCategoryDto> clubCategoryList() {
        return clubMapper.clubCategoryList();
    }

    public ClubDto getClubInfo(String clubName) {
        return clubMapper.clubDeteil(clubName);
    }

    public int clubUserInsert(ClubUserDto clubUserDto) {
        return clubMapper.clubUserInsert(clubUserDto);
    }

    public int clubUserDelete(ClubUserDto clubUserDto) {
        return clubMapper.clubUserDelete(clubUserDto);
    }

    public int clubUserIn(ClubUserDto clubUserDto) {
        return clubMapper.clubUserIn(clubUserDto);
    }

    public List<ClubUserDto> clubAppUserList(ClubUserDto clubUserDto) {
        return clubMapper.clubAppUserList(clubUserDto);
    }

    public List<ClubUserDto> clubUserList(ClubUserDto clubUserDto) {
        return clubMapper.clubUserList(clubUserDto);
    }

    public int clubUserInCheck(ClubUserDto clubUserDto) {return clubMapper.clubUserInCheck(clubUserDto); }

    public int clubAppUserCheck(ClubUserDto clubUserDto) {

    return clubMapper.clubAppUserCheck(clubUserDto); }

}
