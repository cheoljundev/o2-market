package com.o2.site.trade.service;

import com.o2.site.trade.dao.TradeMapper;
import com.o2.site.trade.dto.ApplicationDto;
import com.o2.site.trade.dto.ImageDto;
import com.o2.site.trade.dto.SearchDto;
import com.o2.site.trade.dto.TradeMainDto;
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
                file.transferTo(new File("C:/workspace/multi/semi/o2-market/src/main/resources/static/images/trade/app/"+uploadName));
                images.add(uploadName);
            }
            System.out.println(images);
            result=1;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        tradeMapper.insertApp(ad);
        int no = ad.getTrade_No();
        for(String image : images){
            ImageDto imageDto = new ImageDto(no, image);
            tradeMapper.insertImg(imageDto);
        }
        return result;
    }


    public ArrayList<TradeMainDto> selectMainList() {
        return tradeMapper.selectMainList();
    }

    public ArrayList<TradeMainDto> selectSearchList(SearchDto searchDto) {
        if(searchDto.getCategory().equals("전체")){
            searchDto.setCategory("");
        }
        return tradeMapper.selectSearchList(searchDto);
    }
}
