package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@Builder
public class AdminOrderListDto {
    private Long orderNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private String image;
    private String title;
    private String recipientName;
    @NumberFormat(pattern = "#,###")
    private Long halfPrice;
    private String stateName;

    // Builder 패턴을 사용할 때 enum 값을 설정하는 메서드
    public static class AdminOrderListDtoBuilder {
        public AdminOrderListDtoBuilder stateName(int state) {
            switch (state) {
                case 0:
                    this.stateName = OrderState.WAITING.getStateName();
                    break;
                case 1:
                    this.stateName = OrderState.SHIPPING.getStateName();
                    break;
                case 2:
                    this.stateName = OrderState.CONFIRMED.getStateName();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid state: " + state);
            }
            return this;
        }
    }
}
