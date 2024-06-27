package com.o2.site.member.dao;

import com.o2.site.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Param;

public interface JoinMapper {
    void join(@Param("memberDTO") String memberDTO, String userRole);
    MemberDTO findById(String id);
}
