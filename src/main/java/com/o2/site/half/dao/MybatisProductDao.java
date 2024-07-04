package com.o2.site.half.dao;

import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.product.InsertProductDto;
import com.o2.site.half.dto.product.UpdateProductDto;
import com.o2.site.half.dto.product.UserListProductDto;
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
    public List<UserListProductDto> findRange(int start, int end, SearchCond searchCond) {
        return productMapper.findRangeWithConditions(start, end, searchCond);
    }

    @Override
    public Product findByProductNo(Long productNo) {
        return productMapper.findByProductNo(productNo);
    }

    @Override
    public void updateProduct(UpdateProductDto updateProductDto) {
        productMapper.updateProduct(updateProductDto);
    }

    @Override
    public int findPages(int pageSize) {
        int count = productMapper.count();
        if (count == 0) {
            return 0;
        }
        return (int) Math.ceil((double) count / pageSize);
    }

    @Override
    public int findPages(int pageSize, SearchCond searchCond) {
        int count = productMapper.countWithConditions(searchCond);
        if (count == 0) {
            return 0;
        }
        return (int) Math.ceil((double) count / pageSize);
    }
}
