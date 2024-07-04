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
    private Long buyerMemberNo;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String deliveryMemo;
    private Long invoice;
    private int state;
}
