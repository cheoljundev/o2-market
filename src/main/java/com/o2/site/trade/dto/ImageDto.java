package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageDto {
    private int tradeNo;
    private String imageAddress;

    public ImageDto(int tradeNo, String imageAddress) {
        this.tradeNo = tradeNo;
        this.imageAddress = imageAddress;
    }
}
