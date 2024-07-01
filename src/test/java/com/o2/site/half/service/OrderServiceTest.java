package com.o2.site.half.service;

import com.o2.site.config.O2Application;
import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.dto.AdminOrderDetailDto;
import com.o2.site.half.dto.AdminOrderListDto;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import com.o2.site.upload.dao.UploadDao;
import com.o2.site.upload.domain.UploadImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = O2Application.class)
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    UploadDao uploadDao;

    @Test
    void findByOrderNo() {
        // given
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드2")
                .categoryCode("cg_life")
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
                .build());

        // when
        AdminOrderDetailDto order = orderService.findByOrderNo(1L);
        Long orderNoResult = order.getOrderNo();

        // then
        assertThat(orderNoResult).isEqualTo(1L);
    }

    @Test
    void findAll() {
        // given
        Long orderNo = 1L;
        uploadDao.insertImage(UploadImage.builder()
                .imageNo(1L)
                .imageName("image1.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(orderNo)
                .build());

        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
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
                .build());

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
        List<AdminOrderListDto> orders1 = orderService.findAll(orderSearchCond1);
        String imageName = orders1.get(0).getImage().getImageName();

        List<AdminOrderListDto> orders2 = orderService.findAll(orderSearchCond2);
        String title = orders2.get(0).getTitle();

        List<AdminOrderListDto> orders3 = orderService.findAll(orderSearchCond3);
        String recipientName = orders3.get(0).getRecipientName();

        List<AdminOrderListDto> orders4 = orderService.findAll(orderSearchCond4);
        String recipientPhone = orders4.get(0).getStateName();

        // then
        assertThat(imageName).isEqualTo("image1.jpg");
        assertThat(title).isEqualTo("한번 사용한 아이패드");
        assertThat(recipientName).isEqualTo(orderSearchCond3.getRecipientName());
        assertThat(recipientPhone).isEqualTo(orderSearchCond4.getRecipientPhone());
    }

    @Test
    void findRange() {
        // given
        uploadDao.insertImage(UploadImage.builder()
                .imageNo(1L)
                .imageName("image1.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());
        uploadDao.insertImage(UploadImage.builder()
                .imageNo(1L)
                .imageName("image2.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(2L)
                .build());

        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
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
                .build());

        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(2L)
                .productNo(1L)
                .title("한번 사용한 아이패드2")
                .categoryCode("cg_life")
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
                .build());

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
        List<AdminOrderListDto> orders1 = orderService.findRange(1, 2, orderSearchCond1);
        String imageName = orders1.get(0).getImage().getImageName();

        List<AdminOrderListDto> orders2 = orderService.findRange(1, 2, orderSearchCond2);
        String title = orders2.get(0).getTitle();

        List<AdminOrderListDto> orders3 = orderService.findRange(1, 2, orderSearchCond3);
        String recipientName = orders3.get(0).getRecipientName();

        List<AdminOrderListDto> orders4 = orderService.findRange(1, 2, orderSearchCond4);
        String recipientPhone = orders4.get(0).getStateName();

        // then
        assertThat(imageName).isEqualTo("image1.jpg");
        assertThat(title).isEqualTo("한번 사용한 아이패드");
        assertThat(recipientName).isEqualTo(orderSearchCond3.getRecipientName());
        assertThat(recipientPhone).isEqualTo(orderSearchCond4.getRecipientPhone());
    }

    @Test
    void insertOrder() {
        // given
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
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
                .build());
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(2L)
                .productNo(1L)
                .title("한번 사용한 아이패드2")
                .categoryCode("cg_life")
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
                .build());
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(3L)
                .productNo(1L)
                .title("한번 사용한 아이패드3")
                .categoryCode("cg_life")
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
                .build());

        OrderSearchCond orderSearchCond = OrderSearchCond.builder()
                .build();

        // when
        List<AdminOrderListDto> orders = orderService.findAll(orderSearchCond);
        AdminOrderListDto order = orders.get(0);
        int size = orders.size();
        assertThat(size).isEqualTo(3);
        assertThat(order.getTitle()).isEqualTo("한번 사용한 아이패드3");
    }

    @Test
    void updateOrder() {
        // given
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
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
                .build());

        UpdateOrderDto updateOrderDto1 = UpdateOrderDto.builder()
                .orderNo(1L)
                .invoice(123456789L)
                .state(1)
                .build();

        // when
        orderService.updateOrder(updateOrderDto1);

        // then
        AdminOrderDetailDto order = orderService.findByOrderNo(1L);
        assertThat(order.getInvoice()).isEqualTo(updateOrderDto1.getInvoice());
    }

    @Test
    void deleteOrder() {
        // given
        Long orderNo = 1L;
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
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
                .build());

        // when
        orderService.deleteOrder(orderNo);

        // then
        AdminOrderDetailDto order = orderService.findByOrderNo(1L);
        assertThat(order).isNull();
    }

    @Test
    void findPages() {
        // given
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(1L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
                .price(100000L)
                .halfPrice(50000L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .buyerMemberNo(2L)
                .buyerMemberId("user02")
                .buyerPhone("01056781234")
                .recipientName("김첨지")
                .recipientPhone("01056781234")
                .recipientAddress("서울시 강남구")
                .deliveryMemo("부재시 경비실에 맡겨주세요")
                .build());
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(2L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
                .price(100000L)
                .halfPrice(50000L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .buyerMemberNo(2L)
                .buyerMemberId("user02")
                .buyerPhone("01056781234")
                .recipientName("김첨지")
                .recipientPhone("01056781234")
                .recipientAddress("서울시 강남구")
                .deliveryMemo("부재시 경비실에 맡겨주세요")
                .build());
        orderService.insertOrder(InsertOrderDto.builder()
                .orderNo(3L)
                .productNo(1L)
                .title("한번 사용한 아이패드")
                .categoryCode("cg_life")
                .price(100000L)
                .halfPrice(50000L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .buyerMemberNo(2L)
                .buyerMemberId("user02")
                .buyerPhone("01056781234")
                .recipientName("김첨지")
                .recipientPhone("01056781234")
                .recipientAddress("서울시 강남구")
                .deliveryMemo("부재시 경비실에 맡겨주세요")
                .build());
        OrderSearchCond orderSearchCond = OrderSearchCond.builder()
                .build();

        // when
        int pages = orderService.findPages(orderSearchCond, 10);

        // then
        assertThat(pages).isEqualTo(1);
    }
}