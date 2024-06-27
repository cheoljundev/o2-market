package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TradeMainDto {
    private int tradeNo;
    private String imageAddress;
    private String title;
    private int price;
    private String category;
}
