package com.o2.site.half.service;

import com.o2.site.half.dao.ProductDao;
import com.o2.site.half.dao.SearchCond;
import com.o2.site.half.domain.Product;
import com.o2.site.half.dto.product.*;
import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.service.MemberService;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.domain.UploadImage;
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
    private final MemberMapper memberMapper;

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
                    .tradeNo(product.getTradeNo())
                    .build()).get(0).getStoredImageName();

            String title = tradeService.getBoard(Integer.parseInt(String.valueOf(product.getTradeNo()))).getTitle();
            int price = tradeService.getBoard(Integer.parseInt(String.valueOf(product.getTradeNo()))).getPrice();

            products.add(AdminProductListDto.builder()
                    .productNo(product.getProductNo())
                    .createdAt(product.getCreatedAt())
                    .title(title)
                    .tradeNo(product.getTradeNo())
                    .thumbnail(storedImageName)
                    .price((long) price)
                    .halfPrice((long) price / 2)
                    .state(product.getState())
                    .build());
        });
        return products;
    }

    public List<UserListProductDto> findRange(int start, int end, SearchCond searchCond) {
        return productDao.findRange(start, end, searchCond);
    }

    public AdminProductDetailDto findByProductNo(Long productNo) {
        Product product = productDao.findByProductNo(productNo);
        String title = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getTitle();
        Long memberNo = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getMemberNo();
        String id = memberMapper.findByMemberNo(memberNo).getId();
        String address = memberMapper.findByMemberNo(memberNo).getAddress();
        Long price = (long) tradeService.getBoard(Integer.parseInt(String.valueOf(product.getTradeNo()))).getPrice() / 2;
        return AdminProductDetailDto.builder()
                .productNo(product.getProductNo())
                .tradeNo(product.getTradeNo())
                .title(title)
                .sellerMemberNo(memberNo)
                .sellerMemberId(id)
                .sellerAddress(address)
                .adminMemo(product.getAdminMemo())
                .price(price)
                .state(ProductState.code(product.getState()))
                .build();
    }

    public int findPages(int pageSize) {
        return productDao.findPages(pageSize);
    }

    public int findPages(int pageSize, SearchCond searchCond) {
        return productDao.findPages(pageSize, searchCond);
    }

    public UserProductDetailDto findByProductNoForUser(Long productNo) {
        Product product = productDao.findByProductNo(productNo);
        String title = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getTitle();
        String content = tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getContent();
        Long price = Long.valueOf(tradeService.getBoard(Integer.valueOf(String.valueOf(product.getTradeNo()))).getPrice());
        List<UploadImage> images = uploadService.findImages(UploadImageDto.builder()
                .tradeNo(product.getTradeNo())
                .build());
        return UserProductDetailDto.builder()
                .productNo(product.getProductNo())
                .title(title)
                .content(content)
                .price(price)
                .price(price)
                .halfPrice(price / 2)
                .createdAt(product.getCreatedAt())
                .uploadImages(images)
                .build();
    }
}
