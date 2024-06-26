package com.o2.site.trade.dao;

import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TradeMapper {

    void insertApp(ApplicationDto ad);
    void insertImg(ImageDto imageDto);

    ArrayList<TradeMainDto> selectMainList();

    ArrayList<TradeMainDto> selectSearchList(SearchDto searchDto);

    String getCg(SearchDto searchDto);

    TradeDomain getBoard(String tradeNo);

    ArrayList<String> getImages(String tradeNo);

    WishListDto getWishCount(int memberNo, String tradeNo);

    void upVisitCount(String tradeNo);

    int deleteBoard(String tradeNo);

    int addWish(WishListDto wishListDto);
}
