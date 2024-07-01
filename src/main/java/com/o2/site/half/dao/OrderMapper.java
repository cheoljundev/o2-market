package com.o2.site.half.dao;

import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    Order findByOrderNo(Long orderNo);
    List<Order> findAll(OrderSearchCond orderSearchCond);
    List<Order> findRange(@Param("start") int start, @Param("end") int end, @Param("orderSearchCond") OrderSearchCond orderSearchCond);
    void insertOrder(InsertOrderDto insertOrderDto);
    void updateOrder(UpdateOrderDto updateOrderDto);
    void deleteOrder(Long orderNo);
    int count(OrderSearchCond orderSearchCond);
}
