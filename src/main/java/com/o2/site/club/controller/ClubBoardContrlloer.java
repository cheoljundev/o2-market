package com.o2.site.club.controller;

import com.o2.site.club.function.ClubFunction;
import com.o2.site.club.dto.ClubBoardDto;
import com.o2.site.club.dto.ClubUserDto;
import com.o2.site.club.dto.PageDto;
import com.o2.site.club.service.ClubBoardService;
import com.o2.site.club.service.ClubService;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/club/board")
public class ClubBoardContrlloer {

    @Autowired
    ClubBoardService clubBoardService;

    @Autowired
    ClubService clubService;
    @Autowired
    UploadService uploadService;

    @GetMapping("/list")
    public void listGo(@RequestParam("clubName") String clubName, Model model, @AuthenticationPrincipal CustomUserDetails user) {
        long loginUserNo = ClubFunction.getUserNo(user, model);
        ClubUserDto clubUserDto = new ClubUserDto();
        clubUserDto.setUserNo(loginUserNo);
        clubUserDto.setClubName(clubName);
        int userInCheck = clubService.clubUserInCheck(clubUserDto);

        model.addAttribute("userInCheck", userInCheck);
    }

    @GetMapping("/detail")
    public void detailGo(@RequestParam("clubBoardId") long clubBoardId, Model model) {
        ClubBoardDto clubBoardDto = clubBoardService.clubBoardDeteil(clubBoardId);
        List<UploadImage> uploadImageList = uploadService.findImages(UploadImageDto.builder().clubBoardId(clubBoardId).build());

        model.addAttribute("uploadImageList",uploadImageList);
        model.addAttribute("clubBoardDto",clubBoardDto);
    }

    @GetMapping("/create")
    public void createGo() {}

    @PostMapping("/create")
    public void createAction(ClubBoardDto clubBoardDto, @RequestParam(value = "images", required = false) List<MultipartFile> images, @AuthenticationPrincipal CustomUserDetails user) throws IOException {

        // 추후 로그인 아이디로 수정 start
        long loginUserNo = user.getUser().getMemberRoles().get(0).getMemberNo();
        clubBoardDto.setWriter(loginUserNo);
        // 추후 로그인 아이디로 수정 End
        clubBoardService.createClubBoard(clubBoardDto);

        clubBoardDto.setClubBoardId(clubBoardService.getClubBoardSeq());


        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                UploadImageDto uploadImageDto = new UploadImageDto();
                uploadImageDto.setImage(images.get(i));
                uploadImageDto.setClubBoardId(clubBoardDto.getClubBoardId());
                uploadService.insertImage(uploadImageDto);
            }
        }

    }

    @GetMapping("/getList")
    @ResponseBody
    public ResponseEntity<?> getListClub(PageDto pageDto, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="clubName", required=false)String clubName) {
        System.out.println(pageDto);
        String cntPerPage = "9";
        int total = clubBoardService.clubBoardListCount(pageDto, clubName);
        if (nowPage == null) {
            nowPage = "1";
        } else if (nowPage == null) {
            nowPage = "1";
        }
        pageDto = new PageDto(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage), pageDto.getKeyword(),pageDto.getSearchValue());

        Map<String, Object> response = new HashMap<>();
        response.put("paging", pageDto);
        response.put("viewAll", clubBoardService.getClubBoardList(pageDto, clubName));

        return ResponseEntity.ok(response);

    }
}

