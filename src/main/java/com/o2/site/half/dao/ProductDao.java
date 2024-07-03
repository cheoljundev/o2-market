package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.product.InsertProductDto;
import com.o2.site.half.dto.product.UpdateProductDto;
import com.o2.site.half.dto.product.UserListProductDto;

import java.util.List;

public interface ProductDao {
    void insertProduct(InsertProductDto insertProductDto);
    List<Product> findRange(int start, int end);
    List<UserListProductDto> findRange(int start, int end, SearchCond searchCond);
    Product findByProductNo(Long productNo);
    void updateProduct(UpdateProductDto updateProductDto);
    int findPages(int pageSize);
    int findPages(int pageSize, SearchCond searchCond);
}
