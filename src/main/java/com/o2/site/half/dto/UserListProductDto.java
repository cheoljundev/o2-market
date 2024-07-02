package com.o2.site.half.dto;

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
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    @NumberFormat(pattern = "#,###")
    private Long price;
    @NumberFormat(pattern = "#,###")
    private Long halfPrice;
    private Integer isDone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String adminMemo;
    private String categoryCode;
    private String title;
    private String storedImageName;
}
