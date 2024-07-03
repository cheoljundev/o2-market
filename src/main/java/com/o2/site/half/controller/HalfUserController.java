package com.o2.site.half.controller;

import com.o2.site.half.dao.ProductSearchCond;
import com.o2.site.half.dto.Pagination;
import com.o2.site.half.dto.UserListProductDto;
import com.o2.site.half.service.ProductService;
import com.o2.site.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/half")
@RequiredArgsConstructor
public class HalfUserController {

    private final TradeService tradeService;
    private final ProductService productService;

    @GetMapping("/list")
    public String list(Model model,
                            @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "searchTitle", required = false) String searchTitle,
                          @RequestParam(value = "page", defaultValue = "1") Integer currentPage
                       ) {

        ProductSearchCond productSearchCond = ProductSearchCond.builder()
                .categoryCode(category)
                .title(searchTitle)
                .build();

        int pagesLength = productService.findPages(10, productSearchCond);
        Pagination pagination = new Pagination(
                currentPage,
                pagesLength,
                10
        );

        List<UserListProductDto> products = productService.findRange(pagination.getStartElement(),  pagination.getEndElement(),
                productSearchCond);
        model.addAttribute("categories", tradeService.getCategory());
        model.addAttribute("pagination", pagination);
        model.addAttribute("category", category);
        model.addAttribute("searchTitle", searchTitle);
        model.addAttribute("products", products);
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
