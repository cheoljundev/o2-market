package com.o2.site.trade.controller;

import com.o2.site.member.service.MemberService;
import com.o2.site.trade.domain.AdvDomain;
import com.o2.site.trade.domain.TradeDomain;
import com.o2.site.trade.dto.AdvDetailDto;
import com.o2.site.trade.dto.AdvListDto;
import com.o2.site.trade.dto.TradeMainDto;
import com.o2.site.trade.service.PaginationService;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin")
public class TradeAdminController {
    @Autowired
    TradeService tradeService;
    @Autowired
    PaginationService pagination;
    @Autowired
    UploadService uploadService;
    @Autowired
    MemberService memberService;
    @GetMapping("/trade_admin")
    public String trade_admin(){
        return "/trade/admin/trade_admin";
    }

    //관리자 페이지 신청 리스트 조회
    @GetMapping("/trade_admin_application")
    public String trade_admin_application(Model model, @RequestParam(value = "page",defaultValue = "1") int page){
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
        return "/trade/admin/trade_admin_application";
    }
    //관리자 페이지 신청 상세 조회
    @GetMapping("/trade_admin_approve")
    public String trade_admin_approve(Model model, int tradeNo) {
        TradeDomain tradeDomain = tradeService.getBoard(tradeNo);
        System.out.println(tradeDomain);
        ArrayList<String> imageList = tradeService.getImages(tradeNo);
        System.out.println(imageList);
        model.addAttribute("tradeDomain", tradeDomain);
        model.addAttribute("imageList", imageList);
        return "/trade/admin/trade_admin_approve";
    }
    //관리자-신청 승인
    @GetMapping("/approve")
    public String admin_approve(String tradeNo){
        int result = tradeService.approveBoard(tradeNo);
        if(result>0){
            System.out.println("승인");
        }
        return "/trade/admin/trade_admin_application";
    }
    //관리자-신청 거절
    @GetMapping("/reject")
    public String admin_reject(String tradeNo){
        System.out.println(tradeNo);
        int result = tradeService.rejectBoard(tradeNo);
        if(result>0){
            System.out.println("거절");
        }
        return "/trade/admin/trade_admin_application";
    }
    //광고 디테일
    @GetMapping("/trade_adv_detail")
    public String trade_adv_detail(Model model, String advNo){
        System.out.println(advNo);
        AdvDetailDto advDetailDto = tradeService.getAdvDetail(advNo);
        model.addAttribute("advDetail", advDetailDto);
        return "/trade/admin/trade_adv_detail";
    }
    //관리자 광고 리스트
    @GetMapping("/trade_adv_list")
    public String trade_adv_list(Model model, @RequestParam(value = "page",defaultValue = "1") int page){
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
        return "/trade/admin/trade_adv_list";
    }
    //관리자 광고 등록 페이지
    @GetMapping("/trade_adv_regist")
    public String trade_adv_regist(){
        return "/trade/admin/trade_adv_regist";
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
        return "redirect:/admin/trade_adv_list";
    }
    //관리자 광고 삭제
    @GetMapping("/trade_adv_delete")
    public String deleteAdv(String advNo){
        System.out.println(advNo);
        int result = tradeService.deleteAdv(advNo);
        tradeService.deleteImpages(Integer.parseInt(advNo));
        return "redirect:/admin/trade_adv_list";
    }
}
