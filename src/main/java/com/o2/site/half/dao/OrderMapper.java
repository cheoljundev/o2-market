package com.o2.site.half.dao;

import com.o2.site.half.dto.order.UserOrderListDto;
import com.o2.site.half.dto.order.AdminOrderDetailDto;
import com.o2.site.half.dto.order.AdminOrderJoinListDto;
import com.o2.site.half.dto.order.InsertOrderDto;
import com.o2.site.half.dto.order.UpdateOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    AdminOrderDetailDto findByOrderNo(Long orderNo);
    AdminOrderDetailDto findByProductNo(Long productNo);
    List<UserOrderListDto> findAll(SearchCond searchCond);
    List<AdminOrderJoinListDto> findRange(@Param("start") int start, @Param("end") int end, @Param("searchCond") SearchCond searchCond);
    void insertOrder(InsertOrderDto insertOrderDto);
    void updateOrder(UpdateOrderDto updateOrderDto);
    void deleteOrder(Long orderNo);
    int count(SearchCond searchCond);
}
