package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AdminProductListDto {
    private Long productNo;
    private Date createdAt;
    private Long tradeNo;
    private String thumbnail;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    private Long halfPrice;
    private Integer stateName;
}
