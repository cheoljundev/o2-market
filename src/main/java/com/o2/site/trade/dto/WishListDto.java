package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishListDto {
    private Long memberNo;
    private int tradeNo;
    private int count;
}
