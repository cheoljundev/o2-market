package com.o2.site.club.controller;

import com.o2.site.club.function.ClubFunction;
import com.o2.site.club.dto.*;
import com.o2.site.club.service.ClubBoardService;
import com.o2.site.club.service.ClubScheduleService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/club")
public class ClubContrlloer {

    @Autowired
    ClubService clubService;

    @Autowired
    ClubBoardService clubBoardService;

    @Autowired
    ClubScheduleService scheduleService;

    @Autowired
    UploadService uploadService;

    @GetMapping("/main")
    public void mainGo(@AuthenticationPrincipal CustomUserDetails user, Model model){
        long loginUserNo = ClubFunction.getUserNo(user, model);
    }

    @GetMapping("/create")
    public void createGo() {}

    @GetMapping("/detail")
    public void detailGo (@RequestParam("clubName") String clubName, Model model, @AuthenticationPrincipal CustomUserDetails user){
        ClubDto clubDto = clubService.getClubInfo(clubName);
        List<Integer> boardIds = clubBoardService.getClubBoardId(clubName);
        List<String> uploadImageStrs = new ArrayList<>();
        for (Integer num : boardIds) {
            long numInt = num;
            List<UploadImage> uploadTemp = uploadService.findImages(UploadImageDto.builder().clubBoardId(numInt).build());
            for (UploadImage uploadImage : uploadTemp) {
                uploadImageStrs.add(uploadImage.getStoredImageName());
            }
        }

        long loginUserNo = ClubFunction.getUserNo(user, model);
        ClubUserDto clubUserDto = new ClubUserDto();
        clubUserDto.setUserNo(loginUserNo);
        clubUserDto.setClubName(clubDto.getClubName());
        int userInCheck = clubService.clubUserInCheck(clubUserDto);
        int appUserCheck = clubService.clubAppUserCheck(clubUserDto);

        List<ScheduleDto> scheduleDto = scheduleService.scheduleDetailList(clubName);

        model.addAttribute("clubDto",clubDto);
        model.addAttribute("uploadImageStrs",uploadImageStrs);
        model.addAttribute("scheduleDto",scheduleDto);
        model.addAttribute("userInCheck", userInCheck);
        model.addAttribute("appUserCheck", appUserCheck);


    }

    @GetMapping("/userList")
    public void userListGo(){}

    @PostMapping("/createAction")
    public String createAction(ClubDto clubDto, @RequestParam(value = "image", required = false) MultipartFile image , Model model,  @AuthenticationPrincipal CustomUserDetails user) throws IOException {
        System.out.println(clubDto);
        System.out.println(image);
        // 추후 로그인 아이디로 수정 start
        long loginUserNo = ClubFunction.getUserNo(user, model);
        clubDto.setReaderNo(loginUserNo);
        // 추후 로그인 아이디로 수정 end

        clubService.createClub(clubDto);
        ClubUserDto clubUserDto = new ClubUserDto();
        clubUserDto.setClubName(clubDto.getClubName());
        clubUserDto.setUserNo(clubDto.getReaderNo());
        clubService.clubUserInsert(clubUserDto);
        clubService.clubUserIn(clubUserDto);
        if (!image.isEmpty()) {
            UploadImageDto uploadImageDto = new UploadImageDto();
            uploadImageDto.setImage(image);
            uploadImageDto.setClubName(clubDto.getClubName());
            uploadService.insertImage(uploadImageDto);
        }



        return "redirect:main";
    }

    @GetMapping("/getCategory")
    @ResponseBody
    public List<ClubCategoryDto> getCategory() {
        return clubService.clubCategoryList();
    }

    @GetMapping("/getList")
    @ResponseBody
    public ResponseEntity<?> getListClub(PageDto pageDto, Model model
            , @RequestParam(value="nowPage", required=false)String nowPage) {
        System.out.println(pageDto.toString());
        String cntPerPage = "9";
        int total = clubService.clubListCount(pageDto);
        if (nowPage == null) {
            nowPage = "1";
        } else if (nowPage == null) {
            nowPage = "1";
        }
        pageDto = new PageDto(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),pageDto.getKeyword(),pageDto.getSearchValue());

        Map<String, Object> response = new HashMap<>();
        response.put("paging", pageDto);
        response.put("viewAll", clubService.getClubList(pageDto));

        return ResponseEntity.ok(response);

    }

    @PostMapping("/userInAction")
    public String userInActionn(ClubUserDto clubUserDto) {

        clubService.clubUserInsert(clubUserDto);

        return "redirect:main";
    }

    @GetMapping("/getClubInUserList")
    @ResponseBody
    public List<ClubUserDto> getClubInUserList(ClubUserDto clubUserDto) {

        return clubService.clubUserList(clubUserDto);
    }

    @GetMapping("/getClubAppUserList")
    @ResponseBody
    public List<ClubUserDto> getClubAppUserList(ClubUserDto clubUserDto) {
        return clubService.clubAppUserList(clubUserDto);
    }

    @PostMapping("/userDeleteAcation")
    @ResponseBody
    public void userDeleteUser(ClubUserDto clubUserDto) {

        clubService.clubUserDelete(clubUserDto);
    }

    @PostMapping("/userOk")
    @ResponseBody
    public void userOk(ClubUserDto clubUserDto) {
        clubService.clubUserIn(clubUserDto);
    }
}
