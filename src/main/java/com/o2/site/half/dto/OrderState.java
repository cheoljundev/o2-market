package com.o2.site.half.dto;

public enum OrderState {
    WAITING("발송대기"),
    SHIPPING("배송중"),
    CONFIRMED("구매확정");

    private final String stateName;

    OrderState(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }
}
