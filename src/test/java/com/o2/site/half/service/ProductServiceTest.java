package com.o2.site.half.service;

import com.o2.site.config.O2Application;
import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.*;
import com.o2.site.upload.dao.UploadMapper;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private UploadMapper uploadMapper;

    @Test
    void insertProduct() {
        productService.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());

        AdminProductDetailDto product = productService.findByProductNo(99L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(99L);
    }

    @Test
    void updateProduct() {
        productService.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());


        productService.updateProduct(UpdateProductDto.builder()
                .productNo(99L)
                .isDone(ProductState.DONE)
                .adminMemo("test")
                .build());

        AdminProductDetailDto product = productService.findByProductNo(99L);

        Assertions.assertThat(product.getAdminMemo()).isEqualTo("test");
    }

    @Test
    void findRange() {
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test1.jpg")
                .storedImageName("test1.jpg")
                .tradeNo(1L)
                .build());
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test1.jpg")
                .storedImageName("test1.jpg")
                .tradeNo(2L)
                .build());

        List<AdminProductListDto> products = productService.findRange(1, 2);
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void findByProductNo() {
        productService.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());

        AdminProductDetailDto product = productService.findByProductNo(99L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(99L);
    }

    @Test
    void findPages() {
        int pages = productService.findPages(10);
        Assertions.assertThat(pages).isEqualTo(1);
    }
}