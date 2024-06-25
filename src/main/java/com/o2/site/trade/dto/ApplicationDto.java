package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDto {
    private int Trade_No;
    private int Member_No;
    private String title;
    private String category;
    private String content;
    private int price;

}
