package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class InsertOrderDto {
    private Long orderNo;
    private Long productNo;
    private String title;
    private String categoryCode;
    private String image;
    private Long price;
    private Long halfPrice;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    private Long buyerMemberNo;
    private String buyerMemberId;
    private String buyerPhone;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String deliveryMemo;
}
