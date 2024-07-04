package com.o2.site.half.dto.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductState {
    WAITING("매입 대기", 0),
    DONE("매입 완료", 1),
    SOLD("판매 완료", 2),
    UNKNOWN("알 수 없음", 3);

    private final String stateName;
    private final int code;

    public static ProductState code(int code) {
        for (ProductState state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid ProductState code: " + code);
    }

}
