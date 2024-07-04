package com.o2.site.half.service;

import com.o2.site.config.O2Application;
import com.o2.site.half.dao.OrderDao;
import com.o2.site.half.dao.SearchCond;
import com.o2.site.half.dto.order.*;
import com.o2.site.half.dto.product.InsertProductDto;
import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.domain.Member;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.member.dto.MemberDTO;
import com.o2.site.trade.dto.ApplicationDto;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.dao.UploadMapper;
import com.o2.site.upload.domain.UploadImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class OrderServiceTest {
    @Autowired
    private TradeService tradeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private UploadMapper uploadMapper;

    @BeforeEach
    void setUp() throws IOException {
        // member
        for (int i = 0; i < 11; i++) {
            memberMapper.insertMember(Member.builder()
                    .memberNo((long) i + 1)
                    .id("user" + (i + 1))  // 수정: 문자열 연결 순서 변경
                    .password("1234")
                    .name("김누구" + i)
                    .phoneNumber("01012345678")
                    .address("서울시 강남구")
                    .mileage(100000)
                    .build());
        }

        // trade
        for (int i = 0; i < 10; i++) {
            ApplicationDto applicationDto = new ApplicationDto();
            applicationDto.setTradeNo(i + 1);
            applicationDto.setMemberNo(1L);  // 수정 필요 시 확인
            applicationDto.setTitle("한번 사용한 아이패드" + (i + 1));
            applicationDto.setCategory("cg_life");
            applicationDto.setContent("아이패드 1세대 팝니다.");
            applicationDto.setAddress("서울시 강남구");
            applicationDto.setPrice(100000);
            tradeService.insertApp(applicationDto);
        }

        // product
        for (int i = 0; i < 10; i++) {
            productService.insertProduct(InsertProductDto.builder()
                    .productNo((long) i + 1)
                    .tradeNo((long) i + 1)
                    .build());
        }

        // upload
        for (int i = 0; i < 10; i++) {
            uploadMapper.insertImage(UploadImage.builder()
                    .imageNo((long) i + 1)
                    .tradeNo((long) i + 1)
                    .imageName("test.jpg")
                    .storedImageName("upload.jpg")
                    .build());
        }

        // order
        for (int i = 0, j = 1; i < 10; i++, j++){

            CustomUserDetails customUserDetails = new CustomUserDetails(MemberDTO.builder()
                    .memberNo((long) j)
                    .mileage(100000)
                    .build());

            orderService.insertOrder(InsertOrderDto.builder()
                    .orderNo((long) i + 1)
                    .productNo((long) i + 1)
                    .recipientName("김첨지")
                    .recipientPhone("01056781234")
                    .recipientAddress("서울시 강남구")
                    .deliveryMemo("부재시 경비실에 맡겨주세요")
                    .mileage(50000L)
                    .build(), customUserDetails);
        }



    }

    @Test
    void insertOrder() {
    }

    @Test
    void findByOrderNo() {

        // when
        AdminOrderDetailDto order = orderService.findByOrderNo(1L);

        // then
        Assertions.assertThat(order.getOrderNo()).isEqualTo(1L);
    }

    @Test
    void findRange() {
        // given
        SearchCond searchCond = SearchCond.builder()
                .buyerPhone("01012345678")
                .build();

        // when
        List<AdminOrderListDto> orders = orderService.findRange(1, 10, searchCond);
        int size = orders.size();

        // then
        Assertions.assertThat(size).isEqualTo(10);
    }

    @Test
    void updateOrder() {


        UpdateOrderDto updateOrderDto = UpdateOrderDto.builder()
                .orderNo(1L)
                .invoice(123456789L)
                .state(OrderState.SHIPPING)
                .build();

        // when
        orderService.updateOrder(updateOrderDto);

        // then
        AdminOrderDetailDto order = orderService.findByOrderNo(1L);
        Assertions.assertThat(order.getInvoice()).isEqualTo(updateOrderDto.getInvoice());
    }

    @Test
    void deleteOrder() {
        // when
        orderService.deleteOrder(1L);

        // then
        AdminOrderDetailDto order = orderService.findByOrderNo(1L);
        Assertions.assertThat(order).isNull();
    }

    @Test
    void findPages() {
        // given
        SearchCond searchCond = SearchCond.builder()
                .buyerPhone("01012345678")
                .build();

        // when
        int pages = orderService.findPages(searchCond, 10);

        // then
        Assertions.assertThat(pages).isEqualTo(1);
    }
}