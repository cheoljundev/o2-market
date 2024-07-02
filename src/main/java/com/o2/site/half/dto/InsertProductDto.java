package com.o2.site.half.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertProductDto {
    private Long productNo;
    private Long tradeNo;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    private Long halfPrice;
}
