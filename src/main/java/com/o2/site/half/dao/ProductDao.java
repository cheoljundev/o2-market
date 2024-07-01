package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.UpdateProductDto;

import java.util.List;

public interface ProductDao {
    void insertProduct(InsertProductDto insertProductDto);
    List<Product> findRange(int start, int end);
    Product findByProductNo(Long productNo);
    void updateProduct(UpdateProductDto updateProductDto);
    int findPages(int pageSize);
}
