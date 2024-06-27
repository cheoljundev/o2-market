package com.o2.site.half.service;

import com.o2.site.half.dao.OrderDao;
import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
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

    public List<Order> findAll(OrderSearchCond orderSearchCond){
        return orderDao.findAll(orderSearchCond);
    }
    public Order findByOrderNo(Long orderNo){
        return orderDao.findByOrderNo(orderNo);
    }
    public List<Order> findRange(int start, int end, OrderSearchCond orderSearchCond){
        return orderDao.findRange(start, end, orderSearchCond);
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

    public List<SearchCond> getSearchCond(){
        List<SearchCond> searchConds = new ArrayList<>();
        searchConds.add(SearchCond.builder().field("buyerMemberId").name("구매자 아이디").build());
        searchConds.add(SearchCond.builder().field("buyerPhone").name("구매자 전화번호").build());
        searchConds.add(SearchCond.builder().field("recipientName").name("수령자 이름").build());
        searchConds.add(SearchCond.builder().field("recipientPhone").name("수령자 전화번호").build());
        return searchConds;
    }

    @Data
    @Builder
    public static class SearchCond {
        private String field;
        private String name;
    }
}
