package com.o2.site.half.dto;

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
    private Integer isDone;
    private String adminMemo;

    public static class UpdateProductDtoBuilder {
        public UpdateProductDtoBuilder isDone(ProductState state) {
            String stateName = state.getStateName();
            switch (stateName) {
                case "매입 완료":
                    this.isDone = 1;
                    break;
                case "매입 대기":
                    this.isDone = 0;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid state: " + stateName);
            }
            return this;
        }
    }
}
