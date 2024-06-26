package com.o2.site.half.dao;

import com.o2.site.config.O2Application;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void findByOrderNo() {
        // given
        Long orderNo = 1L;

        // when
        Order order = orderMapper.findByOrderNo(orderNo);
        Long orderNoResult = order.getOrderNo();

        // then
        assertThat(orderNoResult).isEqualTo(orderNo);
    }

    @Test
    void findAll() {
        // given
        OrderSearchCond orderSearchCond1 = OrderSearchCond.builder()
                .buyerMemberId("user02")
                .build();

        OrderSearchCond orderSearchCond2 = OrderSearchCond.builder()
                .buyerPhone("01056781234")
                .build();

        OrderSearchCond orderSearchCond3 = OrderSearchCond.builder()
                .recipientName("김첨지")
                .build();

        OrderSearchCond orderSearchCond4 = OrderSearchCond.builder()
                .recipientPhone("01056781234")
                .build();

        // when
        List<Order> orders1 = orderMapper.findAll(orderSearchCond1);
        String buyerMemberId = orders1.get(0).getBuyerMemberId();

        List<Order> orders2 = orderMapper.findAll(orderSearchCond2);
        String buyerPhone = orders2.get(0).getBuyerPhone();

        List<Order> orders3 = orderMapper.findAll(orderSearchCond3);
        String recipientName = orders3.get(0).getRecipientName();

        List<Order> orders4 = orderMapper.findAll(orderSearchCond4);
        String recipientPhone = orders4.get(0).getRecipientPhone();

        // then
        assertThat(buyerMemberId).isEqualTo(orderSearchCond1.getBuyerMemberId());
        assertThat(buyerPhone).isEqualTo(orderSearchCond2.getBuyerPhone());
        assertThat(recipientName).isEqualTo(orderSearchCond3.getRecipientName());
        assertThat(recipientPhone).isEqualTo(orderSearchCond4.getRecipientPhone());
    }

    @Test
    void findRange() {
    }

    @Test
    void insertOrder() {
        // given
        InsertOrderDto insertOrderDto = InsertOrderDto.builder()
                .productNo(1L)
                .title("한번 사용한 아이패드2")
                .categoryCode("cg_life")
                .image("thumbnail.jpg")
                .price(100000L)
                .halfPrice(50000L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .buyerMemberNo(2L)
                .buyerMemberId("user02")
                .buyerPhone("01056781234")
                .recipientName("김박사")
                .recipientPhone("01056781234")
                .recipientAddress("서울시 강남구")
                .deliveryMemo("부재시 경비실에 맡겨주세요")
                .build();
        orderMapper.insertOrder(insertOrderDto);
        OrderSearchCond orderSearchCond = OrderSearchCond.builder()
                .build();

        // when
        List<Order> orders = orderMapper.findAll(orderSearchCond);
        Order order = orders.get(0);
        int size = orders.size();
        assertThat(size).isEqualTo(2);
        assertThat(order.getTitle()).isEqualTo(insertOrderDto.getTitle());
    }

    @Test
    void updateOrder() {
        // given
        UpdateOrderDto updateOrderDto1 = UpdateOrderDto.builder()
                .orderNo(1L)
                .invoice(123456789L)
                .state(1)
                .build();

        // when
        orderMapper.updateOrder(updateOrderDto1);

        // then
        Order order = orderMapper.findByOrderNo(1L);
        assertThat(order.getInvoice()).isEqualTo(updateOrderDto1.getInvoice());
        assertThat(order.getState()).isEqualTo(updateOrderDto1.getState());
    }

    @Test
    void deleteOrder() {
        // given
        Long orderNo = 1L;

        // when
        orderMapper.deleteOrder(orderNo);

        // then
        Order order = orderMapper.findByOrderNo(orderNo);
        assertThat(order).isNull();
    }
}