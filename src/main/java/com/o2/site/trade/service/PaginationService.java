package com.o2.site.trade.service;

import com.o2.site.trade.dto.AdvListDto;
import com.o2.site.trade.dto.MyListDto;
import com.o2.site.trade.dto.TradeMainDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PaginationService {
    public List<TradeMainDto> paginate(List<TradeMainDto> list, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return list.subList(start, end);
    }
    public List<MyListDto> mylistpaginate(List<MyListDto> list, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return list.subList(start, end);
    }
    public AdvListDto rndadv(List<AdvListDto> advList){
        //광고 랜덤 선택
        Random random = new Random();
        int rand=0;
        try {
            rand = random.nextInt(advList.size());
        }catch (Exception e){
            return null;
        }
        AdvListDto randomAdv = advList.isEmpty() ? null : advList.get(rand);
        return randomAdv;
    }

    public List<AdvListDto> advpaginate(ArrayList<AdvListDto> list, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return list.subList(start, end);
    }
}
