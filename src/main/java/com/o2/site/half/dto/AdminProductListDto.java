package com.o2.site.half.dto;

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
    private String stateName;

    public static class AdminProductListDtoBuilder {
        public AdminProductListDtoBuilder stateName(Integer stateCode) {
            switch (stateCode.intValue()) {
                case 0:
                    this.stateName = "매입 대기";
                    break;
                case 2:
                    this.stateName = "매입 완료";
                    break;
                default:
                    this.stateName = "알수없음";
                    break;
            }
            return this;
        }
    }
}
