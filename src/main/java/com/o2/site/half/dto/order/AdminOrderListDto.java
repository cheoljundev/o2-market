package com.o2.site.half.dto.order;

import com.o2.site.upload.domain.UploadImage;
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
    private UploadImage image;
    private String title;
    private String recipientName;
    @NumberFormat(pattern = "#,###")
    private Long price;
    private String state;

    public static class AdminOrderListDtoBuilder {
        public AdminOrderListDto.AdminOrderListDtoBuilder state(int state) {
            this.state = OrderState.code(state).getStateName();
            return this;
        }
    }

}
