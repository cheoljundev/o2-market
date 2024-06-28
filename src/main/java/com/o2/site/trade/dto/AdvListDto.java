package com.o2.site.trade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdvListDto {
    private int advNo;
    private String title;
    private String advName;
    private String companyName;
    private String companyLink;
    private String content;
    private String storedImageName;
}
