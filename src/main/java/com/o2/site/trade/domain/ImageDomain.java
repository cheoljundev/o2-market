package com.o2.site.trade.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDomain {
    private int Trade_No;
    private int Image_No;
    private String Image_Address;


    @Override
    public String toString() {
        return "ImageDomain{" +
                "Trade_No=" + Trade_No +
                ", Image_No=" + Image_No +
                ", Image_Address='" + Image_Address + '\'' +
                '}';
    }
}
