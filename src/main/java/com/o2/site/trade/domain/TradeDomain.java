package com.o2.site.trade.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class TradeDomain {
    private int Trade_No;
    private int Member_No;
    private String title;
    private String category;
    private String content;
    private int visit_count;
    private int price;
    private String approve;
    private String Is_Trade;
    private Date Post_Date;


    @Override
    public String toString() {
        return "TradeDomain{" +
                "Trade_No=" + Trade_No +
                ", Member_No=" + Member_No +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", visit_count=" + visit_count +
                ", price=" + price +
                ", approve='" + approve + '\'' +
                ", Is_Trade='" + Is_Trade + '\'' +
                ", Post_Date=" + Post_Date +
                '}';
    }
}
