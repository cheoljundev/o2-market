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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/half")
@RequiredArgsConstructor
public class HalfAdminController {

    private final OrderService orderService;

    @GetMapping({"", "/", "/order" })
    public String order(@ModelAttribute OrderSearchCond orderSearchCond,
                        @RequestParam(value = "searchField", defaultValue =  "") String searchField,
                        Model model) {

        // 검색 조건에 따라 검색값을 설정
        String searchValue = null;
        if (orderSearchCond.getBuyerMemberId() != null) {
            searchValue = orderSearchCond.getBuyerMemberId();
        } else if (orderSearchCond.getBuyerPhone() != null) {
            searchValue = orderSearchCond.getBuyerPhone();
        } else if (orderSearchCond.getRecipientName() != null) {
            searchValue = orderSearchCond.getRecipientName();
        } else if (orderSearchCond.getRecipientPhone() != null) {
            searchValue = orderSearchCond.getRecipientPhone();
        }

        List<Order> orders = orderService.findAll(orderSearchCond);
        List<AdminOrderListDto> adminOrderListDtos = new ArrayList<>();
        orders.stream().forEach(order -> {
            adminOrderListDtos.add(AdminOrderListDto.builder()
                            .orderNo(order.getOrderNo())
                            .createAt(order.getCreateAt())
                            .image(order.getImage())
                            .title(order.getTitle())
                            .recipientName(order.getRecipientName())
                            .halfPrice(order.getHalfPrice())
                            .stateName(order.getState())
                            .build());
        });

        model.addAttribute("orders", adminOrderListDtos);
        model.addAttribute("searchConds", orderService.getSearchCond());
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchValue", searchValue);
        return "/half/admin/order";
    }
}
