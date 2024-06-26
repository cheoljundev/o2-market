package com.o2.site.half.controller;

import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.AdminOrderListDto;
import com.o2.site.half.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/half")
@RequiredArgsConstructor
public class HalfAdminController {

    private final OrderService orderService;

    @GetMapping({"", "/", "/order" })
    public String order(@ModelAttribute OrderSearchCond orderSearchCond, Model model) {
        List<Order> orders = orderService.findAll(orderSearchCond);
        List<AdminOrderListDto> adminOrderListDtos = new ArrayList<>();
        orders.stream().forEach(order -> {
            String stateName = null;
            switch (order.getState()) {
                case 0:
                    stateName = "발송대기";
                    break;
                case 1:
                    stateName = "배송중";
                    break;
                case 2:
                    stateName = "구매확정";
                    break;
            }
            adminOrderListDtos.add(AdminOrderListDto.builder()
                            .orderNo(order.getOrderNo())
                            .createAt(order.getCreateAt())
                            .image(order.getImage())
                            .title(order.getTitle())
                            .recipientName(order.getRecipientName())
                            .halfPrice(order.getHalfPrice())
                            .stateName(stateName)
                            .build());
        });
        model.addAttribute("orders", adminOrderListDtos);
        return "/half/admin/order";
    }
}
