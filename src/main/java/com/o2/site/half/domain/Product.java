package com.o2.site.half.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {
    private Long productNo;
    private Long tradeNo;
    private Long sellerMember;
    private String sellerMemberId;
    private String sellerPhone;
    private Long halfPrice;
    private Integer isDone;
    private Date createdAt;
    private String adminMemo;
}
