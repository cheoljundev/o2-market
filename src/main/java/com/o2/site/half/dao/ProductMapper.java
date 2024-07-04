package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.product.InsertProductDto;
import com.o2.site.half.dto.product.UpdateProductDto;
import com.o2.site.half.dto.product.UserListProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void insertProduct(InsertProductDto insertProductDto);
    List<Product> findRange(@Param("start") int start, @Param("end") int end);
    List<UserListProductDto> findRangeWithConditions(@Param("start") int start, @Param("end") int end,
                                                     @Param("searchCond") SearchCond searchCond);
    Product findByProductNo(Long productNo);
    void updateProduct(UpdateProductDto updateProductDto);
    int count();
    int countWithConditions(SearchCond searchCond);

}
