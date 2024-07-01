package com.o2.site.half.dao;

import com.o2.site.config.O2Application;
import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.ProductState;
import com.o2.site.half.dto.UpdateProductDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = O2Application.class)
@Transactional
class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    void insertProduct() {
        productDao.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());

        Product product = productDao.findByProductNo(99L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(99L);
    }

    @Test
    void findRange() {
        List<Product> products = productDao.findRange(1, 2);
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void findByProductNo() {
        productDao.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());

        Product product = productDao.findByProductNo(99L);

        Assertions.assertThat(product.getProductNo()).isEqualTo(99L);
    }

    @Test
    void updateProduct() {
        productDao.insertProduct(InsertProductDto.builder()
                .productNo(99L)
                .tradeNo(1L)
                .sellerMemberNo(1L)
                .sellerMemberId("user01")
                .sellerPhone("01012345678")
                .halfPrice(5000L)
                .build());


        productDao.updateProduct(UpdateProductDto.builder()
                .productNo(99L)
                .isDone(ProductState.DONE)
                .adminMemo("test")
                .build());

        Product product = productDao.findByProductNo(99L);

        Assertions.assertThat(product.getIsDone()).isEqualTo(1);
        Assertions.assertThat(product.getAdminMemo()).isEqualTo("test");
    }

    @Test
    void findPages() {
        int pages = productDao.findPages(10);
        Assertions.assertThat(pages).isEqualTo(1);
    }
}