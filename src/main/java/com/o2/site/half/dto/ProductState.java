package com.o2.site.half.dto;

import lombok.Getter;

@Getter
public enum ProductState {
    WAITING("매입 대기"),
    DONE("매입 완료");

    private final String stateName;

    ProductState(String stateName) {
        this.stateName = stateName;
    }

}
