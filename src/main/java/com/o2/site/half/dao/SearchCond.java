package com.o2.site.half.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCond {
    private String buyerMemberId;
    private String buyerPhone;
    private String recipientName;
    private String recipientPhone;
    private String title;
    private String categoryCode;
    private int state;
}
