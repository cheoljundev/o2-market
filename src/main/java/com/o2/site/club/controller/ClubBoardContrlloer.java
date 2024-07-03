package com.o2.site.club.controller;

import com.o2.site.club.dto.ClubBoardDto;
import com.o2.site.club.dto.ClubCategoryDto;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.service.ClubBoardService;
import com.o2.site.club.service.ClubService;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/club/board")
public class ClubBoardContrlloer {

    @Autowired
    ClubBoardService clubBoardService;

    @Autowired
    UploadService uploadService;

    @GetMapping("/list")
    public void listGo() {}

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
    @RequestBody
    public ResponseEntity<?> getListClub(ClubBoardDto clubBoardDto, @PageableDefault(size = 9) Pageable pageable) {
        return ResponseEntity.ok(clubBoardService.getClubBoardList(clubBoardDto, pageable));
    }
}
