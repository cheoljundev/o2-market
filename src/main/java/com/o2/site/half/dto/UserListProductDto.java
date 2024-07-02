package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserListProductDto {
    private Long productNo;
    private Long tradeNo;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    private Long price;
    private Long halfPrice;
    private Integer isDone;
    private Date createdAt;
    private String adminMemo;
    private String categoryCode;
    private String title;
    private String storedImageName;
}
