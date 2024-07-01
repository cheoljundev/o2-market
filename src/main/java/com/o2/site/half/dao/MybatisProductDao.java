package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.UpdateProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MybatisProductDao implements ProductDao{

    private final ProductMapper productMapper;

    @Override
    public void insertProduct(InsertProductDto insertProductDto) {
        productMapper.insertProduct(insertProductDto);
    }

    @Override
    public List<Product> findRange(int start, int end) {
        return productMapper.findRange(start, end);
    }

    @Override
    public Product findByProductNo(Long productNo) {
        return productMapper.findByProductNo(productNo);
    }

    @Override
    public void updateProduct(UpdateProductDto updateProductDto) {
        productMapper.updateProduct(updateProductDto);
    }
}
