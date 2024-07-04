package com.o2.site.half.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDto {
    private Long productNo;
    private String adminMemo;
    private int state;

    public static class UpdateProductDtoBuilder {
        public UpdateProductDtoBuilder state(ProductState state) {
            this.state = state.getCode();
            return this;
        }
    }
}
