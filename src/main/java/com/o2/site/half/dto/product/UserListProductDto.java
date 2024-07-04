package com.o2.site.half.dto.product;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@Builder
public class UserListProductDto {
    private Long productNo;
    private Long tradeNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String title;
    @NumberFormat(pattern = "#,###")
    private Long price;
    @NumberFormat(pattern = "#,###")
    private Long halfPrice;
    private int state;
    private String categoryCode;
    private String adminMemo;
    private String storedImageName;
}
