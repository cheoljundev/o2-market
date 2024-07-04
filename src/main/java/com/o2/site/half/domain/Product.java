package com.o2.site.half.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Product {
    private Long productNo;
    private Long tradeNo;
    private Date createdAt;
    private String adminMemo;
    private int state;
}
