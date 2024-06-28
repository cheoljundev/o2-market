package com.o2.site.half.controller;

import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.dto.AdminOrderDetailDto;
import com.o2.site.half.dto.AdminOrderListDto;
import com.o2.site.half.dto.UpdateOrderDto;
import com.o2.site.half.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/half")
@RequiredArgsConstructor
public class HalfAdminController {

    private final OrderService orderService;

    @GetMapping
    private String index() {
        return "redirect:/admin/half/order";
    }

    @GetMapping( "/order" )
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

        List<AdminOrderListDto> orders = orderService.findAll(orderSearchCond);

        model.addAttribute("orders", orders);
        model.addAttribute("searchConds", orderService.getSearchCond());
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchValue", searchValue);
        return "/half/admin/order";
    }

    @ResponseBody
    @PostMapping("/order")
    public AdminOrderDetailDto detail(@RequestBody Long orderNo) {
        return orderService.findByOrderNo(orderNo);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/order/{orderNo}/invoice")
    public void updateInvoice(@PathVariable("orderNo") Long orderNo, @RequestBody Long invoice) {
        if (orderService.findByOrderNo(orderNo).getInvoice() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "송장번호가 이미 등록되었습니다.");
        }
        int invoiceLength = String.valueOf(invoice).length();
        if (invoiceLength != 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "송장번호는 12자리로 입력해주세요.");
        }
        orderService.updateOrder(UpdateOrderDto.builder()
                .orderNo(orderNo)
                .invoice(invoice)
                .state(1)
                .build());
    }
}
