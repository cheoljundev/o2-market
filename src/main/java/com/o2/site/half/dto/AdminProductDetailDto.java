package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AdminProductDetailDto {
    private Long productNo;
    private Date createdAt;
    private Long tradeNo;
    private String thumbnail;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private Long halfPrice;
    private String adminMemo;
}
