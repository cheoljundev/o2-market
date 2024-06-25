package com.o2.site.trade.controller;

import com.o2.site.trade.dto.ApplicationDto;
import com.o2.site.trade.dto.SearchDto;
import com.o2.site.trade.dto.TradeMainDto;
import com.o2.site.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    TradeService tradeService;
    //전체 리스트
    @GetMapping("/trade_main")
    public void trade_main(Model model){
        ArrayList<TradeMainDto> mainlist = tradeService.selectMainList();
        System.out.println(mainlist);
        model.addAttribute("mainlist", mainlist);
        model.addAttribute("cg","전체");

    }
    //검색 리스트
    @GetMapping("/trade_search")
    public String trade_sarch(Model model, SearchDto searchDto){
        ArrayList<TradeMainDto> searchList = tradeService.selectSearchList(searchDto);
        System.out.println(searchList);
        if(searchDto.getCategory()==""){
            searchDto.setCategory("전체");
        }
        String cg = searchDto.getCategory();
        System.out.println(cg);
        model.addAttribute("mainlist", searchList);
        model.addAttribute("cg",cg);
        return "/trade/trade_main";
    }
    @GetMapping("/trade_application")
    public void trade_application(){}
    @GetMapping("/trade_detail")
    public void trade_detail(){}
    @GetMapping("/trade_admin")
    public void trade_admin(){}
    @GetMapping("/trade_admin_application")
    public void trade_admin_application(){}
    @GetMapping("/trade_admin_approve")
    public void trade_admin_approve(){}
    @GetMapping("/trade_adv_detail")
    public void trade_adv_detail(){}
    @GetMapping("/trade_adv_list")
    public void trade_adv_list(){}
    @GetMapping("/trade_adv_regist")
    public void trade_adv_regist(){}
    //상품 등록 신청
    @PostMapping("/trade_app")
    public String insertApp(ApplicationDto ad, @RequestParam("files") MultipartFile[] files){
        int result = tradeService.insertApp(ad,files);
        if(result>0){
            System.out.println("파일 업로드 성공");
        }else {
            System.out.println("파일 업로드 실패");
        }
        return "redirect:/trade/trade_main";
    }
}
