package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.UpdateProductDto;
import com.o2.site.half.dto.UserListProductDto;

import java.util.List;

public interface ProductDao {
    void insertProduct(InsertProductDto insertProductDto);
    List<Product> findRange(int start, int end);
    List<UserListProductDto> findRange(int start, int end, ProductSearchCond productSearchCond);
    Product findByProductNo(Long productNo);
    void updateProduct(UpdateProductDto updateProductDto);
    int findPages(int pageSize);
    int findPages(int pageSize, ProductSearchCond productSearchCond);
}
