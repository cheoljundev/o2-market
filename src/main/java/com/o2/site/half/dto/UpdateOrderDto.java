package com.o2.site.half.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrderDto {
    private Long orderNo;
    private Long invoice;
}
