package com.o2.site.half.dto.product;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AdminProductDetailDto {
    private Long productNo;
    private Long tradeNo;
    private String title;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerAddress;
    private String adminMemo;
    private Long price;
    private ProductState state;
}
