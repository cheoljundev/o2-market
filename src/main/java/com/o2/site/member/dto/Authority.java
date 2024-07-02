package com.o2.site.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authority {

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }
}
