package com.o2.site.half.dto.order;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class InsertOrderDto {
    private Long orderNo;
    private Long productNo;
    private Long buyerMemberNo;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String deliveryMemo;
}
