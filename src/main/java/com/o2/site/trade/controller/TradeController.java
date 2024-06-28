package com.o2.site.trade.controller;


import com.o2.site.trade.domain.AdvDomain;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/trade")
public class TradeController {
    private static final int PAGE_SIZE = 5;
    private final TradeService tradeService;

    private final UploadService uploadService;

    public TradeController(TradeService tradeService, UploadService uploadService) {
        this.tradeService = tradeService;
        this.uploadService = uploadService;
    }

//    전체 리스트 관리자 페이지 구성 후 approve 조건 추가 예정
    @GetMapping("/trade_main")
    public void trade_main(Model model){
        ArrayList<TradeMainDto> mainlist = tradeService.selectMainList();
        ArrayList<CategoryDto> category = tradeService.getCategory();
        System.out.println(mainlist);
        System.out.println("총 갯수: "+mainlist.size());
        model.addAttribute("mainlist", mainlist);
        model.addAttribute("cg","전체");
        model.addAttribute("category",category);
    }
    //페이징 테스트
    @GetMapping("/trade_main_test")
    public void trade_main(Model model, @RequestParam(defaultValue = "0") int page){
        ArrayList<TradeMainDto> mainlist = tradeService.selectMainList();
        ArrayList<AdvListDto> advList = tradeService.getAdvList();

        List<TradeMainDto> paginatedMainList = paginate(mainlist, page, PAGE_SIZE);

        Random random = new Random();
        int rand=random.nextInt(advList.size());
        AdvListDto firstAdv = advList.isEmpty() ? null : advList.get(rand);

        int totalPages = (int) Math.ceil((double) mainlist.size() / PAGE_SIZE);

        model.addAttribute("mainList", paginatedMainList);
        model.addAttribute("advItem", firstAdv);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }

    // 리스트를 페이지 단위로 자르는 메서드
    private List<TradeMainDto> paginate(List<TradeMainDto> list, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return list.subList(start, end);
    }

    //검색 리스트
    @GetMapping("/trade_search")
    public String trade_sarch(Model model, SearchDto searchDto){
        if(searchDto.getCategory().equals("cg_all")){
            searchDto.setCategory("");
        }
        ArrayList<TradeMainDto> searchList = tradeService.selectSearchList(searchDto);
        System.out.println(searchList);
        System.out.println("총 갯수: "+searchList.size());
        model.addAttribute("mainlist", searchList);
        if(tradeService.getCg(searchDto)==null){
            model.addAttribute("cg","전체");
        }else {
        model.addAttribute("cg",tradeService.getCg(searchDto));
        }
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("category",category);
        return "/trade/trade_main";
    }
    @GetMapping("/trade_application")
    public void trade_application(Model model){
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("category",category);
    }
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
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("category",category);
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
        CheckWishDto checkWishDto = tradeService.checkWish(wishListDto);
        System.out.println("위시리스트"+wishListDto);
        System.out.println("체크리스트"+checkWishDto);
        if(checkWishDto==null){
            int result = tradeService.addWish(wishListDto);
        }else if(checkWishDto.getTradeNo()!=wishListDto.getTradeNo() || checkWishDto.getMemberNo()!=wishListDto.getMemberNo()){
            int result = tradeService.addWish(wishListDto);
        }else {
            System.out.println("중복");
        }
        return "redirect:/trade/trade_detail?tradeNo="+wishListDto.getTradeNo();
    }
    //내 찜목록 보기
    @GetMapping("/trade_mywish")
    public String trade_mywish(Model model){
        int memberNo = 1;
        ArrayList<TradeMainDto> wishList = tradeService.myWishList(memberNo);
        System.out.println(wishList);
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("category",category);
        model.addAttribute("mainlist",wishList);
        return "/trade/trade_main";
    }
    @GetMapping("/trade_admin")
    public void trade_admin(){}
    //관리자 페이지 신청 리스트 조회
    @GetMapping("/trade_admin_application")
    public void trade_admin_application(Model model){
        ArrayList<TradeMainDto> mainlist = tradeService.selectAppList();
        System.out.println(mainlist);
        System.out.println("총 갯수: "+mainlist.size());
        model.addAttribute("mainlist", mainlist);
        model.addAttribute("cg","전체");
    }
    //관리자 페이지 신청 상세 조회
    @GetMapping("/trade_admin_approve")
    public void trade_admin_approve(Model model, String tradeNo){
        TradeDomain tradeDomain = tradeService.getBoard(tradeNo);
        System.out.println(tradeDomain);
        ArrayList<String> imageList = tradeService.getImages(tradeNo);
        System.out.println(imageList);
        model.addAttribute("tradeDomain",tradeDomain);
        model.addAttribute("imageList",imageList);
    }
    //관리자-신청 승인
    @GetMapping("/approve")
    public String admin_approve(String tradeNo){
        int result = tradeService.approveBoard(tradeNo);
        if(result>0){
            System.out.println("승인");
        }
        return "redirect:/trade/trade_admin_application";
    }
    //관리자-신청 거절
    @GetMapping("/reject")
    public String admin_reject(String tradeNo){
        System.out.println(tradeNo);
        int result = tradeService.rejectBoard(tradeNo);
        if(result>0){
            System.out.println("거절");
        }
        return "redirect:/trade/trade_admin_application";
    }
    @GetMapping("/trade_adv_detail")
    public void trade_adv_detail(){}
    //관리자 광고 리스트
    @GetMapping("/trade_adv_list")
    public void trade_adv_list(Model model){
        ArrayList<AdvListDto> advList = tradeService.getAdvList();
        System.out.println(advList);
        model.addAttribute("advList",advList);
    }
    //관리자 광고 등록
    @GetMapping("/trade_adv_regist")
    public void trade_adv_regist(){}
    //상품 등록 신청
    @PostMapping("/trade_app")
    public String insertApp(ApplicationDto ad, @RequestParam("files") MultipartFile[] files){
        int result = tradeService.insertApp(ad);
        System.out.println("no: "+ad.getTradeNo());
        System.out.println(result);
        try{
            for(MultipartFile image : files){
                UploadImageDto uploadImageDto = UploadImageDto.builder()
                        .image(image)
                        .tradeNo(Long.valueOf(ad.getTradeNo()))
                        .build();
                uploadService.insertImage(uploadImageDto);
            }
        }catch (Exception e){
            System.out.println("이미지 등록 실패");
        }
        return "redirect:/trade/trade_main";
    }
    //관리자 광고 등록
    @PostMapping("/trade_adv_regist")
    public String insertAdv(AdvDomain advDomain, @RequestParam("image") MultipartFile image){
        System.out.println(advDomain);
        int result = tradeService.insertAdv(advDomain);
        try{
            UploadImageDto uploadImageDto = UploadImageDto.builder()
                    .image(image)
                    .advNo(Long.valueOf(advDomain.getAdvNo()))
                    .build();
            uploadService.insertImage(uploadImageDto);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("이미지 등록 실패");
        }
        return "redirect:/trade/trade_adv_list";
    }
}
