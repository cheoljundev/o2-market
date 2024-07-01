package com.o2.site.trade.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Data
public class AdvDomain {
    private int advNo=1;
    private String title;
    private String advName;
    private String companyName;
    private String companyLink;
    private String content;
}
