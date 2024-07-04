package com.o2.site.half.dto.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState {
    WAITING("발송대기", 0),
    SHIPPING("배송중", 1),
    CONFIRMED("구매확정", 2);

    private final String stateName;
    private final int code;

    public static OrderState code(int code) {
        for (OrderState state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid OrderState code: " + code);
    }


}
