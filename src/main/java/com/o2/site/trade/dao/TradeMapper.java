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

    TradeDomain getBoard(String tradeNo);

    ArrayList<String> getImages(String tradeNo);

    int getWishCount(String tradeNo) throws BindingException;

    void upVisitCount(String tradeNo);

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

    List<TradeDomain> findTradeByCategoryCode(String categoryCode);
}
