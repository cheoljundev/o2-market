package com.o2.site.half.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSearchCond {
    private String buyerMemberId;
    private String buyerPhone;
    private String recipientName;
    private String recipientPhone;
}
