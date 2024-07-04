package com.o2.site.half.service;

import com.o2.site.config.O2Application;
import com.o2.site.half.dao.ProductDao;
import com.o2.site.half.dao.ProductMapper;
import com.o2.site.half.dao.SearchCond;
import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.product.*;
import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.domain.Member;
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

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(classes = O2Application.class)
class ProductServiceTest {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private UploadMapper uploadMapper;

    @BeforeEach
    void setUp() {
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

        // upload
        for (int i = 0; i < 10; i++) {
            uploadMapper.insertImage(UploadImage.builder()
                    .imageNo((long) i + 1)
                    .tradeNo((long) i + 1)
                    .imageName("test.jpg")
                    .storedImageName(UUID.randomUUID().toString() + ".jpg")
                    .build());
        }

        // product
        for (int i = 0; i < 10; i++) {
            productService.insertProduct(InsertProductDto.builder()
                    .productNo((long) i + 1)
                    .tradeNo((long) i + 1)
                    .build());
        }
    }

    @Test
    void insertProduct() {

    }

    @Test
    void updateProduct() {
        productService.updateProduct(UpdateProductDto.builder()
                .productNo(1L)
                .adminMemo("test")
                .build());

        AdminProductDetailDto product = productService.findByProductNo(1L);

        Assertions.assertThat(product.getAdminMemo()).isEqualTo("test");
    }

    @Test
    void findRange() {
        List<AdminProductListDto> products = productService.findRange(1, 10);
        Assertions.assertThat(products.size()).isEqualTo(10);
    }

    @Test
    void findRangeWithCondition() {
        SearchCond searchCond = SearchCond.builder()
                .categoryCode("cg_life")
                .build();

        List<UserListProductDto> products = productService.findRange(1, 10, searchCond);
        Assertions.assertThat(products.size()).isEqualTo(10);
    }

    @Test
    void findByProductNo() {
        AdminProductDetailDto product = productService.findByProductNo(1L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(1L);
    }

    @Test
    void findPages() {
        int pages = productService.findPages(10);
        Assertions.assertThat(pages).isEqualTo(1);
    }

    @Test
    void findPagesWIthCondition() {
        SearchCond searchCond = SearchCond.builder()
                .categoryCode("cg_life")
                .build();

        int pages = productService.findPages(10, searchCond);
        Assertions.assertThat(pages).isEqualTo(1);
    }

    @Test
    void findByProductNoForUser() {
        UserProductDetailDto product = productService.findByProductNoForUser(1L);

        Assertions.assertThat(product.getTitle()).isEqualTo("한번 사용한 아이패드1");
    }
}