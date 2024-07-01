package com.o2.site.trade.dao;

import com.o2.site.trade.domain.AdvDomain;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.binding.BindingException;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TradeMapper {

    int insertApp(ApplicationDto ad);

    ArrayList<TradeMainDto> selectMainList();

    ArrayList<TradeMainDto> selectSearchList(SearchDto searchDto);

    String getCg(SearchDto searchDto);

    TradeDomain getBoard(int tradeNo);

    ArrayList<String> getImages(int tradeNo);

    int getWishCount(int tradeNo) throws BindingException;

    void upVisitCount(int tradeNo);

    int deleteBoard(String tradeNo);

    int addWish(WishListDto wishListDto);

    ArrayList<TradeMainDto> myWishList(int memberNo);

    ArrayList<TradeMainDto> selectAppList();

    int approveBoard(String tradeNo);

    int rejectBoard(String tradeNo);

    ArrayList<CategoryDto> getCategory();

    CheckWishDto checkWish(WishListDto wishListDto);

    int insertAdv(AdvDomain advDomain);

    ArrayList<AdvListDto> getAdvList();

    AdvDetailDto getAdvDetail(String advNo);

    int deleteAdv(String advNo);

    ArrayList<MyListDto> selectMyList(String memberNo);

    int updateBoard(ApplicationDto ad);

    void deleteImages(int tradeNo);

    List<TradeDomain> findTradeByCategoryCode(String categoryCode);

}
