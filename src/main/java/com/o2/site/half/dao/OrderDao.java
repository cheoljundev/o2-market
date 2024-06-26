package com.o2.site.half.dao;

import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;

import java.util.List;

public interface OrderDao {
    Order findByOrderNo(Long orderNo);
    List<Order> findAll(OrderSearchCond orderSearchCond);
    List<Order> findRange(int start, int end, OrderSearchCond orderSearchCond);
    void insertOrder(InsertOrderDto insertOrderDto);
    void updateOrder(UpdateOrderDto updateOrderDto);
    void deleteOrder(Long orderNo);
}
