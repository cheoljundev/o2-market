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
}
