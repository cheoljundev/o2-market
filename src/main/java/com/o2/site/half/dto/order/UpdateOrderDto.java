package com.o2.site.half.dto.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrderDto {
    private Long orderNo;
    private Long invoice;
    private int state;

    public static class UpdateOrderDtoBuilder {
        public UpdateOrderDto.UpdateOrderDtoBuilder state(OrderState state) {
            this.state = state.getCode();
            return this;
        }
    }
}
