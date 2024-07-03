package com.o2.site.half.dao;

import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.order.*;

import java.util.List;

public interface OrderDao {
    AdminOrderDetailDto findByOrderNo(Long orderNo);
    List<AdminOrderListDto> findRange(int start, int end, SearchCond searchCond);
    void insertOrder(InsertOrderDto insertOrderDto);
    void updateOrder(UpdateOrderDto updateOrderDto);
    void deleteOrder(Long orderNo);
    int findPages(SearchCond searchCond, int pageSize);
}
