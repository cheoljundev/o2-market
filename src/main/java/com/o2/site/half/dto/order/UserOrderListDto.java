package com.o2.site.half.dto.order;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Getter
@ToString
public class UserOrderListDto {
    private Long orderNo;
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private String title;
    @NumberFormat(pattern = "###,###")
    private Long price;
    private String invoice;
    private String state;

    public UserOrderListDto(Long orderNo, String id, Date createAt, String title, Long price, String invoice, Integer state) {
        this.orderNo = orderNo;
        this.id = id;
        this.createAt = createAt;
        this.title = title;
        this.price = price;
        this.invoice = invoice;
        if (invoice == null) {
            this.invoice = OrderState.WAITING.getStateName();
        }
        this.state = OrderState.code(state).getStateName();
    }
}
