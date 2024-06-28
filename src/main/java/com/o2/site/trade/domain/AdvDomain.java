package com.o2.site.trade.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AdvDomain {
    private int advNo;
    private String title;
    private String advName;
    private String companyName;
    private String companyLink;
    private String content;
}
