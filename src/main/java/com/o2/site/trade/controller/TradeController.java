package com.o2.site.trade.controller;


import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.member.service.MemberService;
import com.o2.site.trade.domain.AdvDomain;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.*;
import com.o2.site.trade.service.PaginationService;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    PaginationService pagination;

    @Autowired
    MemberService memberService;
    private final TradeService tradeService;

    private final UploadService uploadService;

    public TradeController(TradeService tradeService, UploadService uploadService) {
        this.tradeService = tradeService;
        this.uploadService = uploadService;
    }

    //전체 리스트
    @GetMapping("/trade_main")
    public void trade_main(Model model, @RequestParam(value = "page",defaultValue = "1") int page){
        int page_size = 5;

        ArrayList<TradeMainDto> mainlist = tradeService.selectMainList();
        ArrayList<CategoryDto> category = tradeService.getCategory();
        ArrayList<AdvListDto> advList = tradeService.getAdvList();

        int adjustPage=page-1;
        //페이지 나누기
        List<TradeMainDto> paginatedMainList = pagination.paginate(mainlist, adjustPage, page_size);

        int totalPages = (int) Math.ceil((double) mainlist.size() / page_size);

        System.out.println(mainlist);
        System.out.println("총 갯수: "+mainlist.size());
        model.addAttribute("mainlist", paginatedMainList);
        model.addAttribute("cg","전체");
        model.addAttribute("category",category);
        model.addAttribute("advlist", pagination.rndadv(advList));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }

    // 리스트를 페이지 단위로 자르는 메서드

    //검색 리스트
    @GetMapping("/trade_search")
    public String trade_sarch(Model model, SearchDto searchDto, @RequestParam(value = "page",defaultValue = "1") int page){
        int page_size = 5;
        if(searchDto.getCategory().equals("cg_all")){
            searchDto.setCategory("");
        }
        ArrayList<TradeMainDto> searchList = tradeService.selectSearchList(searchDto);
        ArrayList<AdvListDto> advList = tradeService.getAdvList();
        System.out.println(searchList);
        System.out.println("총 갯수: "+searchList.size());

        int adjustPage=page-1;
        //페이지 나누기
        List<TradeMainDto> paginatedMainList = pagination.paginate(searchList, adjustPage, page_size);

        int totalPages = (int) Math.ceil((double) searchList.size() / page_size);

        model.addAttribute("mainlist", paginatedMainList);
        if(tradeService.getCg(searchDto)==null){
            model.addAttribute("cg","전체");
        }else {
        model.addAttribute("cg",tradeService.getCg(searchDto));
        }
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("page_category",searchDto.getCategory());
        model.addAttribute("page_keyword",searchDto.getKeyword());
        model.addAttribute("category",category);
        model.addAttribute("advlist", pagination.rndadv(advList));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "/trade/trade_search";
    }
    @GetMapping("/trade_application")
    public void trade_application(Model model){
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("category",category);
    }
    //게시글 한개 조회
    @GetMapping("/trade_detail")
    public void trade_detail(Model model, int tradeNo, @AuthenticationPrincipal CustomUserDetails user){

        Long memberNo = memberService.findMemberNo(user.getUser().getId());
        String isWished;
        CheckWishDto checkWishDto = tradeService.checkWish(tradeNo,memberNo);
        if(checkWishDto==null){
            isWished="찜 하기";
        }else if(checkWishDto.getTradeNo()!=tradeNo || checkWishDto.getMemberNo()!=memberNo){
            isWished="찜 하기";
        }else {
            isWished="찜 해제";
        }
        TradeDomain tradeDomain = tradeService.getBoard(tradeNo);
        System.out.println(tradeDomain);
        tradeService.upVisitCount(tradeNo);
        ArrayList<String> imageList = tradeService.getImages(tradeNo);
        System.out.println(imageList);
        int wishList;
        try {
            wishList = tradeService.getWishCount(tradeNo);
        } catch (BindingException e) {
            wishList=0;
        }
        System.out.println("찜"+wishList);
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("memberNo",memberNo);
        model.addAttribute("isWished",isWished);
        model.addAttribute("category",category);
        model.addAttribute("wishCount",wishList);
        model.addAttribute("tradeDomain",tradeDomain);
        model.addAttribute("imageList",imageList);
    }
    //삭제
    @GetMapping("/trade_delete")
    public String trade_delete(String tradeNo){
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
    public String trade_addWish(Model model, WishListDto wishListDto,@AuthenticationPrincipal CustomUserDetails user){
        Long memberNo = memberService.findMemberNo(user.getUser().getId());
        wishListDto.setMemberNo(memberNo);
        CheckWishDto checkWishDto = tradeService.checkWish(wishListDto);
        System.out.println("위시리스트"+wishListDto);
        System.out.println("체크리스트"+checkWishDto);
        if(checkWishDto==null){
            int result = tradeService.addWish(wishListDto);
        }else if(checkWishDto.getTradeNo()!=wishListDto.getTradeNo() || checkWishDto.getMemberNo()!=wishListDto.getMemberNo()){
            int result = tradeService.addWish(wishListDto);
        }else {
            System.out.println("중복");
            tradeService.deleteWish(wishListDto);
        }
        return "redirect:/trade/trade_detail?tradeNo="+wishListDto.getTradeNo();
    }
    //내 찜목록 보기
    @GetMapping("/trade_mywish")
    public String trade_mywish(Model model, @RequestParam(value = "page",defaultValue = "1") int page, @AuthenticationPrincipal CustomUserDetails user){
        Long memberNo = memberService.findMemberNo(user.getUser().getId());
        int page_size = 5;
        ArrayList<TradeMainDto> wishList = tradeService.myWishList(memberNo);
        ArrayList<AdvListDto> advList = tradeService.getAdvList();
        System.out.println(wishList);
        int adjustPage=page-1;
        ArrayList<CategoryDto> category = tradeService.getCategory();
        //페이지 나누기
        List<TradeMainDto> paginatedMainList = pagination.paginate(wishList, adjustPage, page_size);

        int totalPages = (int) Math.ceil((double) wishList.size() / page_size);

        model.addAttribute("category",category);
        model.addAttribute("mainlist",paginatedMainList);
        model.addAttribute("advlist", pagination.rndadv(advList));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "/trade/trade_mywish";
    }
    @GetMapping("/trade_admin")
    public void trade_admin(){}
    //관리자 페이지 신청 리스트 조회
    @GetMapping("/trade_admin_application")
    public void trade_admin_application(Model model, @RequestParam(value = "page",defaultValue = "1") int page){
        int page_size = 6;
        int adjustPage=page-1;
        ArrayList<TradeMainDto> mainlist = tradeService.selectAppList();
        System.out.println(mainlist);
        //페이지 나누기
        List<TradeMainDto> paginatedMainList = pagination.paginate(mainlist, adjustPage, page_size);
        int totalPages = (int) Math.ceil((double) mainlist.size() / page_size);
        System.out.println("총 갯수: "+mainlist.size());
        model.addAttribute("mainlist", paginatedMainList);
        model.addAttribute("cg","전체");
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }
    //관리자 페이지 신청 상세 조회
    @GetMapping("/trade_admin_approve")
    public void trade_admin_approve(Model model, int tradeNo){
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
    //광고 디테일
    @GetMapping("/trade_adv_detail")
    public void trade_adv_detail(Model model, String advNo){
        System.out.println(advNo);
        AdvDetailDto advDetailDto = tradeService.getAdvDetail(advNo);
        model.addAttribute("advDetail", advDetailDto);
    }
    //관리자 광고 리스트
    @GetMapping("/trade_adv_list")
    public void trade_adv_list(Model model, @RequestParam(value = "page",defaultValue = "1") int page){
        int page_size = 6;
        ArrayList<AdvListDto> advList = tradeService.getAdvList();
        System.out.println(advList);
        int adjustPage=page-1;
        //페이지 나누기
        List<AdvListDto> paginatedMainList = pagination.advpaginate(advList, adjustPage, page_size);

        int totalPages = (int) Math.ceil((double) advList.size() / page_size);
        model.addAttribute("advList",paginatedMainList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }
    //관리자 광고 등록
    @GetMapping("/trade_adv_regist")
    public void trade_adv_regist(){}
    //상품 등록 신청
    @PostMapping("/trade_app")
    public String insertApp(ApplicationDto ad, @RequestParam("files") MultipartFile[] files, @AuthenticationPrincipal CustomUserDetails user){
        ad.setMemberNo(memberService.findMemberNo(user.getUser().getId()));
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
    //관리자 광고 삭제
    @GetMapping("/trade_adv_delete")
    public String deleteAdv(String advNo){
        System.out.println(advNo);
        int result = tradeService.deleteAdv(advNo);
        return "redirect:/trade/trade_adv_list";
    }
    //내 작성글 모두 보기
    @GetMapping("/trade_mylist")
    public String myList(Model model, @RequestParam(value = "page",defaultValue = "1") int page,  @AuthenticationPrincipal CustomUserDetails user){
        int page_size = 5;
        Long memberNo = memberService.findMemberNo(user.getUser().getId());
        System.out.println(memberNo);
        ArrayList<MyListDto> mylist = tradeService.selectMyList(memberNo);
        ArrayList<CategoryDto> category = tradeService.getCategory();
        ArrayList<AdvListDto> advList = tradeService.getAdvList();
        int adjustPage=page-1;
        List<MyListDto> paginatedMainList = pagination.mylistpaginate(mylist, adjustPage, page_size);

        int totalPages = (int) Math.ceil((double) mylist.size() / page_size);

        System.out.println(mylist);

        model.addAttribute("category",category);
        model.addAttribute("mainlist", paginatedMainList);
        model.addAttribute("advlist", pagination.rndadv(advList));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "/trade/trade_mylist";
    }

    @GetMapping("/trade_update")
    public void trade_update(Model model, int tradeNo){
        System.out.println(tradeNo);
        TradeDomain tradeDomain = tradeService.getBoard(tradeNo);
        model.addAttribute("tradeDomain",tradeDomain);
        System.out.println(tradeDomain);
        ArrayList<CategoryDto> category = tradeService.getCategory();
        model.addAttribute("category",category);
    }
    //게시글 수정
    @PostMapping("/trade_update")
    public String trade_update(ApplicationDto ad, @RequestParam("files") MultipartFile[] files){
        System.out.println(ad);
        int reslut = tradeService.updateBoard(ad);
        try{
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    System.out.println("empty");
                    return "redirect:/trade/trade_main";
                }
            }
                tradeService.deleteImpages(ad.getTradeNo());
            for (MultipartFile image : files) {
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
    //거래 완료
    @GetMapping("/trade_end")
    public String trade_end(int tradeNo, @AuthenticationPrincipal CustomUserDetails user, Model model){
        Long memberNo = memberService.findMemberNo(user.getUser().getId());
        model.addAttribute("memberNo",memberNo);
        System.out.println(tradeNo);
        tradeService.endTrade(tradeNo);
        return "redirect:/trade/trade_main";
    }
}
