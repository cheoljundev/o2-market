package com.o2.site.half.dto.product;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Builder
@Data
public class AdminProductListDto {
    private Long productNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private String title;
    private Long tradeNo;
    private String thumbnail;
    @NumberFormat(pattern = "#,###")
    private Long price;
    @NumberFormat(pattern = "#,###")
    private Long halfPrice;
    private String state;

    public static class AdminProductListDtoBuilder {
        public AdminProductListDtoBuilder state(int state) {
            this.state = ProductState.code(state).getStateName();
            return this;
        }
    }
}
