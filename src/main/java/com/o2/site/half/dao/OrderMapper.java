package com.o2.site.half.dao;

import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    public Order findByOrderNo(Long orderNo);
    public List<Order> findAll(OrderSearchCond orderSearchCond);
    public List<Order> findRange(int start, int end, OrderSearchCond orderSearchCond);
    public void insertOrder(InsertOrderDto insertOrderDto);
    public void updateOrder(UpdateOrderDto updateOrderDto);
    public void deleteOrder(Long orderNo);
}
