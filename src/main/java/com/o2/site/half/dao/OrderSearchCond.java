package com.o2.site.half.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchCond {
    private String buyerMemberId;
    private String buyerPhone;
    private String recipientName;
    private String recipientPhone;
}
