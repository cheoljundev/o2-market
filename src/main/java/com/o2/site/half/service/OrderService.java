package com.o2.site.half.service;

import com.o2.site.half.dao.OrderDao;
import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;

    List<Order> findAll(OrderSearchCond orderSearchCond){
        return orderDao.findAll(orderSearchCond);
    }
    Order findByOrderNo(Long orderNo){
        return orderDao.findByOrderNo(orderNo);
    }
    List<Order> findRange(int start, int end, OrderSearchCond orderSearchCond){
        return orderDao.findRange(start, end, orderSearchCond);
    }
    void insertOrder(InsertOrderDto insertOrderDto){
        orderDao.insertOrder(insertOrderDto);
    }
    void updateOrder(UpdateOrderDto updateOrderDto){
        orderDao.updateOrder(updateOrderDto);
    }
    void deleteOrder(Long orderNo){
        orderDao.deleteOrder(orderNo);
    }
}
