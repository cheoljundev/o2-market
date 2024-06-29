package com.o2.site.trade.service;


import com.o2.site.trade.dao.TradeMapper;
import com.o2.site.trade.domain.AdvDomain;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(rollbackFor = Exception.class)
public class TradeService {

    private final TradeMapper tradeMapper;

    @Autowired
    public TradeService(TradeMapper tradeMapper) {
        this.tradeMapper = tradeMapper;
    }

    public int insertApp(ApplicationDto ad) {
        System.out.println(ad);
        int result = tradeMapper.insertApp(ad);
        return result;
    }


    public ArrayList<TradeMainDto> selectMainList() {
        return tradeMapper.selectMainList();
    }

    public ArrayList<TradeMainDto> selectSearchList(SearchDto searchDto) {
        return tradeMapper.selectSearchList(searchDto);
    }

    public String getCg(SearchDto searchDto) {
        return tradeMapper.getCg(searchDto);
    }

    public TradeDomain getBoard(String tradeNo) {
        return tradeMapper.getBoard(tradeNo);
    }

    public ArrayList<String> getImages(String tradeNo) {
        return tradeMapper.getImages(tradeNo);
    }

    public int getWishCount(String tradeNo) throws BindingException {
        return tradeMapper.getWishCount(tradeNo);
    }

    public void upVisitCount(String tradeNo) {
        tradeMapper.upVisitCount(tradeNo);
    }

    public int deleteBoard(String tradeNo) {
        return tradeMapper.deleteBoard(tradeNo);
    }

    public int addWish(WishListDto wishListDto) {
        return tradeMapper.addWish(wishListDto);
    }

    public ArrayList<TradeMainDto> myWishList(int memberNo) {
        return tradeMapper.myWishList(memberNo);
    }

    public ArrayList<TradeMainDto> selectAppList() {
        return tradeMapper.selectAppList();
    }

    public int approveBoard(String tradeNo) {
        return tradeMapper.approveBoard(tradeNo);
    }

    public int rejectBoard(String tradeNo) {
        return tradeMapper.rejectBoard(tradeNo);
    }

    public ArrayList<CategoryDto> getCategory() {
        return tradeMapper.getCategory();
    }

    public CheckWishDto checkWish(WishListDto wishListDto) {
        return tradeMapper.checkWish(wishListDto);
    }

    public int insertAdv(AdvDomain advListDto) {
        return tradeMapper.insertAdv(advListDto);
    }

    public ArrayList<AdvListDto> getAdvList() {
        return tradeMapper.getAdvList();
    }

    public AdvDetailDto getAdvDetail(String advNo) {
        return tradeMapper.getAdvDetail(advNo);
    }

    public int deleteAdv(String advNo) {
        return tradeMapper.deleteAdv(advNo);
    }
}
