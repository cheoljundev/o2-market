package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
    private int no;
    private String address;

    public ImageDto(int no, String address) {
        this.no = no;
        this.address=address;
    }
}
