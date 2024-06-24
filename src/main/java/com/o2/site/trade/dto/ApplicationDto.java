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

    @Override
    public String toString() {
        return "ApplicationDto{" +
                "Member_No=" + Member_No +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                '}';
    }
}
