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

    @Override
    public int findPages(OrderSearchCond orderSearchCond, int pageSIze) {
        int count = orderMapper.count(orderSearchCond);
        return (int) Math.ceil((double) count / pageSIze); // 페이지 수 계산, Math.ceil()은 올림 함수, Math.floor()는 내림 함수, Math.round()는 반올림 함수
    }
}
