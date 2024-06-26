package com.o2.site.trade.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Getter
@Setter
@ToString
public class TradeDomain {
    private int tradeNo;
    private int memberNo;
    private String title;
    private String categoryCode;
    private String content;
    private int visitCount;
    private int price;
    private String address;
    private String approve;
    private String isTrade;
    private Date postDate;

}
