package com.o2.site.half.dto;

import com.o2.site.upload.domain.UploadImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResultTradeDto {
    private int tradeNo;
    private UploadImage thumbnail;
    private String title;
    @NumberFormat(pattern = "#,###")
    private int price;
    @NumberFormat(pattern = "#,###")
    private int halfPrice;
}
