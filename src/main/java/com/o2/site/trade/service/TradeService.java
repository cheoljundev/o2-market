package com.o2.site.trade.service;

import com.o2.site.trade.dao.TradeMapper;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class TradeService {
    private final TradeMapper tradeMapper;

    @Autowired
    public TradeService(TradeMapper tradeMapper) {
        this.tradeMapper = tradeMapper;
    }

    public int insertApp(ApplicationDto ad, MultipartFile[] files) {
        int result = 0;
        ArrayList<String> images = new ArrayList<>();
        System.out.println(ad);
        try{
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                int pos = fileName.lastIndexOf(".");
                String ext = fileName.substring(pos+1);
                String uploadName = uuid+"."+ext;
                System.out.println(uploadName);
                String filePath="C:/workspace/multi/semi/o2-market/src/main/resources/static/images/trade/app/";
                File mkdir = new File(filePath);
                if(!mkdir.exists()) {
                    mkdir.mkdirs();
                }
                file.transferTo(new File(filePath+uploadName));
                images.add(uploadName);
            }
            System.out.println(images);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        tradeMapper.insertApp(ad);
        int no = ad.getTrade_No();
        for(String image : images){
            ImageDto imageDto = new ImageDto(no, image);
            tradeMapper.insertImg(imageDto);
            result=1;
        }
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
}
