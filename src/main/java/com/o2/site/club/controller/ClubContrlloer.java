package com.o2.site.club.controller;

import com.o2.site.club.dto.ClubCategoryDto;
import com.o2.site.club.dto.ClubDto;
import com.o2.site.club.dto.ScheduleDto;
import com.o2.site.club.service.ClubBoardService;
import com.o2.site.club.service.ClubScheduleService;
import com.o2.site.club.service.ClubService;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public void mainGo() {}

    @GetMapping("/create")
    public void createGo() {}

    @GetMapping("/detail")
    public void detailGo (@RequestParam("clubName") String clubName, Model model){
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

        List<ScheduleDto> scheduleDto = scheduleService.scheduleDetailList(clubName);

        model.addAttribute("clubDto",clubDto);
        model.addAttribute("uploadImageStrs",uploadImageStrs);
        model.addAttribute("scheduleDto",scheduleDto);


    }

    @GetMapping("/userList")
    public void userListGo(){}

    @PostMapping("/createAction")
    public String createAction(ClubDto clubDto, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        System.out.println(clubDto);
        System.out.println(image);
        // 추후 로그인 아이디로 수정 start
        clubDto.setReaderNo(1);
        // 추후 로그인 아이디로 수정 end
        clubService.createClub(clubDto);

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
    @RequestBody
    public ResponseEntity<?> getListClub(ClubDto clubDto, @PageableDefault(size = 9) Pageable pageable) {
        return ResponseEntity.ok(clubService.getClubList(clubDto, pageable));
    }

}
