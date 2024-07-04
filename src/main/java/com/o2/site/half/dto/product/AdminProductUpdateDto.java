package com.o2.site.half.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminProductUpdateDto {
    private Long productNo;
    private String adminMemo;
}
