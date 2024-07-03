package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplicationDto {
    private int tradeNo;
    private Long memberNo;
    private String title;
    private String category;
    private String content;
    private String address;
    private int price;

}
