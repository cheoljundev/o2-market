package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@Builder
public class AdminOrderListDto {
    private Long orderNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private String image;
    private String title;
    private String recipientName;
    @NumberFormat(pattern = "#,###")
    private Long halfPrice;
    private String stateName;
}
