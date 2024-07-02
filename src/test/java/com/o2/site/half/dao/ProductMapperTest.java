package com.o2.site.half.dao;

import com.o2.site.config.O2Application;
import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.ProductState;
import com.o2.site.half.dto.UpdateProductDto;
import com.o2.site.half.dto.UserListProductDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void insertProduct() {
        productMapper.insertProduct(InsertProductDto.builder()
                        .productNo(99L)
                        .tradeNo(1L)
                        .sellerMemberNo(1L)
                        .sellerMemberId("user01")
                        .sellerPhone("01012345678")
                        .halfPrice(5000L)
                .build());

        Product product = productMapper.findByProductNo(99L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(99L);
    }

    @Test
    void findRange() {
        List<Product> products = productMapper.findRange(1, 2);
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void findRangeWithConditions() {
        productMapper.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .isDone(ProductState.DONE)
                .build()
        );

        List<UserListProductDto> products = productMapper.findRangeWithConditions(1, 1, ProductSearchCond.builder()
                .categoryCode("cg_life")
                .build());
        Assertions.assertThat(products.size()).isEqualTo(1);
    }

    @Test
    void findByProductNo() {
        productMapper.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());

        Product product = productMapper.findByProductNo(99L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(99L);
    }

    @Test
    void updateProduct() {
        productMapper.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());


        productMapper.updateProduct(UpdateProductDto.builder()
                .productNo(99L)
                .isDone(ProductState.DONE)
                .adminMemo("test")
                .build());

        Product product = productMapper.findByProductNo(99L);

        Assertions.assertThat(product.getIsDone()).isEqualTo(1);
        Assertions.assertThat(product.getAdminMemo()).isEqualTo("test");
    }

    @Test
    void count() {
        int count = productMapper.count();
        Assertions.assertThat(count).isEqualTo(2);
    }
}