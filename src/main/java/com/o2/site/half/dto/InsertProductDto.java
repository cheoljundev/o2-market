package com.o2.site.half.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertProductDto {
    private Long productNo;
    private Long tradeNo;
    private Long sellerMemberNo;
    private String sellerMemberId;
    private String sellerPhone;
    private Long halfPrice;
    private Integer isDone;

    public static class InsertProductDtoBuilder{
        public InsertProductDtoBuilder isDone(ProductState state) {
            switch (state) {
                case WAITING:
                    this.isDone = 0;
                    break;
                case DONE:
                    this.isDone = 1;
                    break;
            }
            return this;
        }

    }
}
