package com.o2.site.half.service;

import com.o2.site.half.dao.OrderDao;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.order.*;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.service.UploadService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;

    public AdminOrderDetailDto findByOrderNo(Long orderNo){
        return orderDao.findByOrderNo(orderNo);
    }
    public List<AdminOrderListDto>  findRange(int start, int end, com.o2.site.half.dao.SearchCond searchCond){
        return orderDao.findRange(start, end, searchCond);
    }
    public void insertOrder(InsertOrderDto insertOrderDto){
        orderDao.insertOrder(insertOrderDto);
    }
    public void updateOrder(UpdateOrderDto updateOrderDto){
        orderDao.updateOrder(updateOrderDto);
    }
    public void deleteOrder(Long orderNo){
        orderDao.deleteOrder(orderNo);
    }

    public int findPages(com.o2.site.half.dao.SearchCond searchCond, int pageSize){
        return orderDao.findPages(searchCond, pageSize);
    }

    public List<SearchCond> getSearchCond(){
        List<SearchCond> searchConds = new ArrayList<>();
        searchConds.add(OrderService.SearchCond.builder().field("buyerMemberId").name("구매자 아이디").build());
        searchConds.add(OrderService.SearchCond.builder().field("buyerPhone").name("구매자 전화번호").build());
        searchConds.add(OrderService.SearchCond.builder().field("recipientName").name("수령자 이름").build());
        searchConds.add(OrderService.SearchCond.builder().field("recipientPhone").name("수령자 전화번호").build());
        return searchConds;
    }

    @Data
    @Builder
    public static class SearchCond {
        private String field;
        private String name;
    }
}
