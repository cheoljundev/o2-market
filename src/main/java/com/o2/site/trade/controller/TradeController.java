package com.o2.site.trade.controller;

import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import com.o2.site.trade.service.TradeService;
import org.apache.ibatis.binding.BindingException;
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
    //전체 리스트 관리자 페이지 구성 후 approve 조건 추가 예정
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
        if(searchDto.getCategory().equals("cg_all")){
            searchDto.setCategory("");
        }
        ArrayList<TradeMainDto> searchList = tradeService.selectSearchList(searchDto);
        System.out.println(searchList);
        model.addAttribute("mainlist", searchList);
        if(tradeService.getCg(searchDto)==null){
            model.addAttribute("cg","전체");
        }else {
        model.addAttribute("cg",tradeService.getCg(searchDto));
        }
        return "/trade/trade_main";
    }
    @GetMapping("/trade_application")
    public void trade_application(){}
    //게시글 한개 조회
    @GetMapping("/trade_detail")
    public void trade_detail(Model model, String tradeNo){
        TradeDomain tradeDomain = tradeService.getBoard(tradeNo);
        System.out.println(tradeDomain);
        tradeService.upVisitCount(tradeNo);
        ArrayList<String> imageList = tradeService.getImages(tradeNo);
        System.out.println(imageList);
        int wishList = 0;
        try {
            wishList = tradeService.getWishCount(tradeNo);
        } catch (BindingException e) {
            wishList=0;
        }
        System.out.println(wishList);
        model.addAttribute("wishCount",wishList);
        model.addAttribute("tradeDomain",tradeDomain);
        model.addAttribute("imageList",imageList);
    }
    //삭제
    @GetMapping("/trade_delete")
    public String rade_delete(String tradeNo){
        int result = tradeService.deleteBoard(tradeNo);
        if(result!=0){
            System.out.println("삭제 성공");
        }else {
            System.out.println("삭제 실패");
        }
        return "redirect:/trade/trade_main";
    }
    //찜하기
    @GetMapping("/trade_addWish")
    public String trade_addWish(WishListDto wishListDto){
        wishListDto.setMemberNo(1);
        int result = tradeService.addWish(wishListDto);
        return "redirect:/trade/trade_detail?tradeNo="+wishListDto.getTradeNo();
    }
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
