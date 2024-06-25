package com.o2.site.half.dao;

import com.o2.site.config.O2Application;
import com.o2.site.half.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

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
        Assertions.assertThat(buyerMemberId).isEqualTo(orderSearchCond1.getBuyerMemberId());
        Assertions.assertThat(buyerPhone).isEqualTo(orderSearchCond2.getBuyerPhone());
        Assertions.assertThat(recipientName).isEqualTo(orderSearchCond3.getRecipientName());
        Assertions.assertThat(recipientPhone).isEqualTo(orderSearchCond4.getRecipientPhone());
    }

    @Test
    void findRange() {
    }

    @Test
    void insertOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void deleteOrder() {
    }
}