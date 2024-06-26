package com.o2.site.half.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Order {
    private Long orderNo;
    private Long productNo;
    private Date createAt;
    private String title;
    private String categoryCode;
    private String image;
    private Long price;
    private Long halfPrice;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    private int state;
    private Long invoice;
    private Long buyerMemberNo;
    private String buyerMemberId;
    private String buyerPhone;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String deliveryMemo;
}
