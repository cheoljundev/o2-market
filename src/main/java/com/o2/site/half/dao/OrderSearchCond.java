package com.o2.site.half.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSearchCond {
    private String BUYER_MEMBER_ID;
    private String BUYER_PHONE;
    private String RECIPIENT_NAME;
    private String RECIPIENT_PHONE;
}
