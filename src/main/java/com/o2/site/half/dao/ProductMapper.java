package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.UpdateProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insertProduct(InsertProductDto insertProductDto);
    List<Product> findRange(@Param("start") int start, @Param("end") int end);
    Product findByProductNo(Long productNo);
    void updateProduct(UpdateProductDto updateProductDto);
    int count();

}
