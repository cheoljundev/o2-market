package com.o2.site.half.service;

import com.o2.site.half.dao.ProductDao;
import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.AdminProductDetailDto;
import com.o2.site.half.dto.AdminProductListDto;
import com.o2.site.half.dto.InsertProductDto;
import com.o2.site.half.dto.UpdateProductDto;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final UploadService uploadService;
    private final TradeService tradeService;

    public void insertProduct(InsertProductDto insertProductDto) {
        tradeService.doneTrade(insertProductDto.getTradeNo());
        productDao.insertProduct(insertProductDto);
    }

    public void updateProduct(UpdateProductDto updateProductDto) {
        productDao.updateProduct(updateProductDto);
    }

    public List<AdminProductListDto> findRange(int start, int end) {
        List<AdminProductListDto> products = new ArrayList<>();
        productDao.findRange(start, end).forEach(product -> {
            String storedImageName = uploadService.findImages(UploadImageDto.builder()
                    .tradeNo(null)
                    .build()).get(0).getStoredImageName();

            String title = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getTitle();
            int price = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getPrice();

            products.add(AdminProductListDto.builder()
                            .productNo(product.getProductNo())
                            .createdAt(product.getCreatedAt())
                            .title(title)
                            .tradeNo(product.getTradeNo())
                            .thumbnail(storedImageName)
                            .price(Long.valueOf(price))
                            .halfPrice(product.getHalfPrice())
                            .stateName(product.getIsDone())
                    .build());
        });
        return products;
    }

    public AdminProductDetailDto findByProductNo(Long productNo) {
        Product product = productDao.findByProductNo(productNo);
        String title = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getTitle();
        return AdminProductDetailDto.builder()
                .productNo(product.getProductNo())
                .tradeNo(product.getTradeNo())
                .title(title)
                .sellerMemberNo(product.getSellerMemberNo())
                .sellerMemberId(product.getSellerMemberId())
                .halfPrice(product.getHalfPrice())
                .adminMemo(product.getAdminMemo())
                .isDone(product.getIsDone())
                .build();
    }

    public int findPages(int pageSize) {
        return productDao.findPages(pageSize);
    }
}
