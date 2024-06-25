package com.o2.site.trade.dao;

import com.o2.site.trade.dto.ApplicationDto;
import com.o2.site.trade.dto.ImageDto;
import com.o2.site.trade.dto.TradeMainDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TradeMapper {

    void insertApp(ApplicationDto ad);
    void insertImg(ImageDto imageDto);

    ArrayList<TradeMainDto> selectMainList();
}
