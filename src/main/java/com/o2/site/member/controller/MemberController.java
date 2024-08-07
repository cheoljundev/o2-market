package com.o2.site.member.controller;

import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.function.ClubFunction;
import com.o2.site.club.service.ClubService;
import com.o2.site.half.dao.SearchCond;
import com.o2.site.half.dto.order.UserOrderListDto;
import com.o2.site.half.service.OrderService;
import com.o2.site.member.domain.Member;
import com.o2.site.member.domain.MemberRole;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.member.dto.MemberJoinDTO;
import com.o2.site.member.service.MemberService;
import com.o2.site.trade.dto.MyListDto;
import com.o2.site.trade.dto.TradeMainDto;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
//@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UploadService uploadService;
    private final ClubService clubService;
    private final OrderService orderService;
    private final TradeService tradeService;



    // 회원가입 페이지 이동
    @GetMapping("/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(MemberJoinDTO memberJoinDTO, Model model) throws IOException {
        if (!memberJoinDTO.getPassword().equals(memberJoinDTO.getPasswordCheck())) {
            model.addAttribute("비밀번호가 일치하지 않습니다.");
            return "/member/join";
        }
        // 비밀번호 암호화
        String encodePassword = bCryptPasswordEncoder.encode(memberJoinDTO.getPassword());

        Member member = Member.builder()
                .id(memberJoinDTO.getId())
                .password(encodePassword)
                .name(memberJoinDTO.getName())
                .phoneNumber(memberJoinDTO.getPhoneNumber())
                .address(memberJoinDTO.getAddress())
                .mileage(0)
                .build();


        memberService.join(member);
        Long memberNo = memberService.findMemberNo(member.getId());

        uploadService.insertImage(UploadImageDto.builder()
                .memberNo(memberNo)
                .image(memberJoinDTO.getImage())
                .build());


        if (memberNo != null) {
            MemberRole memberRole = new MemberRole(memberNo, "ROLE_USER");
            memberService.insertRole(memberRole);
        }
        
        return "member/login";
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/member/myPage_buyList")
    public String myBuyForm(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        List<UserOrderListDto> orders = orderService.findAll(SearchCond.builder()
                .buyerMemberId(customUserDetails.getUsername())
                .build());
        model.addAttribute("orders", orders);
        return "/member/myPage_buyList";
    }

    @GetMapping("/member/myPage_sellList")
    public String mySellForm(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        Long memberNo;
        try {
            memberNo = memberService.findMemberNo(user.getUser().getId());
        }catch (Exception e){
            return "redirect:/login";
        }
        ArrayList<MyListDto> mylist = tradeService.selectMyList(memberNo);
        model.addAttribute("mainlist", mylist);
        return "/member/myPage_sellList";
    }

    @GetMapping("/member/myPage_interestList")
    public String myInterestForm(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        Long memberNo;
        try {
            memberNo = memberService.findMemberNo(user.getUser().getId());
        }catch (Exception e){
            return "redirect:/login";
        }
        ArrayList<TradeMainDto> wishList = tradeService.myWishList(memberNo);
        model.addAttribute("mainlist",wishList);

        return "/member/myPage_interestList";
    }

    @GetMapping("/member/myPage_clubList")
    public String myClubForm(Model model, @AuthenticationPrincipal CustomUserDetails user) {
        long loginUserNo = ClubFunction.getUserNo(user, model);
        List<ClubDto> myClubList = clubService.myPageClubList(loginUserNo);
        model.addAttribute("myClubList", myClubList);
        return "/member/myPage_clubList";
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping("/member/confirmOrder")
    public void confirmOrder(@RequestBody Long orderNo) {
        orderService.confirmOrder(orderNo);
    }
}