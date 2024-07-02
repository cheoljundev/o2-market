package com.o2.site.half.controller;

import com.o2.site.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/half")
@RequiredArgsConstructor
public class HalfUserController {

    private final TradeService tradeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categories", tradeService.getCategory());
        return "half/user/list";
    }

    @GetMapping("/detail/{id}")
    public String detail() {
        return "half/user/detail";
    }

    @GetMapping("/order/{id}")
    public String order() {
        return "half/user/order";
    }
}
