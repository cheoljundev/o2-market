package com.o2.site.half.dao;

import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderDao implements OrderDao{

    private final OrderMapper orderMapper;

    @Override
    public Order findByOrderNo(Long orderNo) {
        return orderMapper.findByOrderNo(orderNo);
    }

    @Override
    public List<Order> findAll(OrderSearchCond orderSearchCond) {
        return orderMapper.findAll(orderSearchCond);
    }

    @Override
    public List<Order> findRange(int start, int end, OrderSearchCond orderSearchCond) {
        return orderMapper.findRange(start, end, orderSearchCond);
    }

    @Override
    public void insertOrder(InsertOrderDto insertOrderDto) {
        orderMapper.insertOrder(insertOrderDto);
    }

    @Override
    public void updateOrder(UpdateOrderDto updateOrderDto) {
        orderMapper.updateOrder(updateOrderDto);
    }

    @Override
    public void deleteOrder(Long orderNo) {
        orderMapper.deleteOrder(orderNo);
    }
}
