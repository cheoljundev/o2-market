package com.o2.site.half.dto.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@Builder
public class AdminOrderJoinListDto {
    private Long orderNo;
    private Long productNo;
    private Long tradeNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private String title;
    @NumberFormat(pattern = "#,###")
    private Long price;
    private String recipientName;
    private int state;

}
