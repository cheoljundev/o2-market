package com.o2.site.half.controller;

import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.dto.*;
import com.o2.site.half.service.OrderService;
import com.o2.site.half.service.ProductService;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/half")
@RequiredArgsConstructor
public class HalfAdminController {

    private final OrderService orderService;
    private final TradeService tradeService;
    private final ProductService productService;
    private final UploadService uploadService;

    @GetMapping
    private String index() {
        return "redirect:/admin/half/order";
    }

    @GetMapping( "/order" )
    public String order(
                        @RequestParam(value = "searchField", defaultValue =  "") String searchField,
                        @RequestParam(value = "searchValue", defaultValue =  "") String searchValue,
                        @RequestParam(value = "page", defaultValue = "1") int currentPage,
                        Model model) {

        OrderSearchCond orderSearchCond = new OrderSearchCond();
        switch (searchField) {
            case "buyerMemberId":
                orderSearchCond.setBuyerMemberId(searchValue);
                break;
            case "buyerPhone":
                orderSearchCond.setBuyerPhone(searchValue);
                break;
            case "recipientName":
                orderSearchCond.setRecipientName(searchValue);
                break;
            case "recipientPhone":
                orderSearchCond.setRecipientPhone(searchValue);
                break;
            default:
                searchField = "";
                searchValue = "";
        }

        int pageSIze = 10;
        int pageLength = orderService.findPages(orderSearchCond, pageSIze);

        Pagination pagination = new Pagination(
                currentPage,
                pageLength,
                pageSIze
        );

        List<AdminOrderListDto> orders = orderService.findRange(
                pagination.getStartElement(),
                pagination.getEndElement(),
                orderSearchCond);

        model.addAttribute("orders", orders);
        model.addAttribute("searchConds", orderService.getSearchCond());
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("pagination", pagination);
        return "/half/admin/order";
    }

    @ResponseBody
    @PostMapping("/order")
    public AdminOrderDetailDto orderDetail(@RequestBody Long orderNo) {
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

    @GetMapping("/event")
    public String event(Model model) {
        model.addAttribute("categories", tradeService.getCategory());
        return "/half/admin/event-console";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/event/draw")
    public void draw(@RequestBody EventResultDto eventResultDto, HttpSession session){
        List<String> categoryCodes = new ArrayList<>();
        List<TradeDomain> tradeDomains = new ArrayList<>();
        List<EventResultTradeDto> resultTrades = new ArrayList<>();

        categoryCodes.addAll(eventResultDto.getCategoryCodes());

        categoryCodes.forEach(categoryCode -> {
            tradeDomains.addAll(tradeService.findTradeByCategoryCode(categoryCode));
        });

        if (tradeDomains.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "추첨 대상 상품이 부족합니다.");
        }

        Collections.shuffle(tradeDomains);
        List<TradeDomain> resultList = tradeDomains.subList(0, eventResultDto.getNumber());

        resultList.forEach(tradeDomain -> {
            UploadImage thumbnail = uploadService.findImages(UploadImageDto.builder()
                    .tradeNo(Long.valueOf(tradeDomain.getTradeNo()))
                    .build()).get(0);

            resultTrades.add(EventResultTradeDto.builder()
                    .title(tradeDomain.getTitle())
                    .thumbnail(thumbnail)
                    .price(tradeDomain.getPrice())
                    .halfPrice(tradeDomain.getPrice() / 2)
                    .build());
        });

        // resultTrades를 모델에 추가합니다.
        log.info("resultTrades: {}", resultTrades);
        session.setAttribute("trades", resultTrades);
    }

    @GetMapping("/event/result")
    public String eventResult(Model model, HttpSession session) {
        // 세션에서 resultTrades를 가져와서 모델에 추가합니다.
        List<EventResultTradeDto> resultTrades;
        resultTrades = (List<EventResultTradeDto>) session.getAttribute("trades");
        model.addAttribute("trades", resultTrades);

        return "/half/admin/event-result";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int currentPage, Model model) {
        int pageSize = 10;
        int pageLength = productService.findPages(pageSize);
        Pagination pagination = new Pagination(
                currentPage,
                pageLength,
                pageSize
        );
        List<AdminProductListDto> products = productService.findRange(pagination.getStartElement(), pagination.getEndElement());
        model.addAttribute("pagination", pagination);
        model.addAttribute("products", products);
        return "/half/admin/list";
    }

    @ResponseBody
    @PostMapping("/list")
    public AdminProductDetailDto listDetail(@RequestBody Long productNo) {
        return productService.findByProductNo(productNo);
    }

}
