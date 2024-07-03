package com.o2.site.half.dto.product;

import com.o2.site.upload.domain.UploadImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProductDetailDto {
    private Long productNo;
    private String title;
    private String content;
    @NumberFormat(pattern = "###,###")
    private Long price;
    @NumberFormat(pattern = "###,###")
    private Long halfPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    private List<UploadImage> uploadImages;
}
