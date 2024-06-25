package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageDto {
    private int no;
    private String address;

    public ImageDto(int no, String address) {
        this.no = no;
        this.address = address;
    }
}
