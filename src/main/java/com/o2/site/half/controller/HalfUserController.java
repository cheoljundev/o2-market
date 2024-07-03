package com.o2.site.half.controller;

import com.o2.site.half.dao.SearchCond;
import com.o2.site.half.dto.Pagination;
import com.o2.site.half.dto.product.UserListProductDto;
import com.o2.site.half.service.ProductService;
import com.o2.site.trade.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/half")
@RequiredArgsConstructor
public class HalfUserController {

    private final TradeService tradeService;
    private final ProductService productService;

    @GetMapping("/list")
    public String list(Model model,
                            @RequestParam(value = "selectedCategory", required = false) String selectedCategory,
                          @RequestParam(value = "searchTitle", required = false) String searchTitle,
                          @RequestParam(value = "page", defaultValue = "1") Integer currentPage
                       ) {

        SearchCond searchCond = SearchCond.builder()
                .categoryCode(selectedCategory)
                .title(searchTitle)
                .build();
        int pagesLength = productService.findPages(10, searchCond);
        Pagination pagination = new Pagination(
                currentPage,
                pagesLength,
                10
        );

        List<UserListProductDto> products = productService.findRange(pagination.getStartElement(),  pagination.getEndElement(),
                searchCond);
        model.addAttribute("categories", tradeService.getCategory());
        model.addAttribute("pagination", pagination);
        model.addAttribute("selectedCategory", selectedCategory);
        model.addAttribute("searchTitle", searchTitle);
        model.addAttribute("products", products);
        return "half/user/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findByProductNoForUser(id));
        return "half/user/detail";
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findByProductNoForUser(id));
        return "half/user/order";
    }

//    @PostMapping("/order")
}
