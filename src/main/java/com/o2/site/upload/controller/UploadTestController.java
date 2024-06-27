package com.o2.site.upload.controller;

import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UploadTestController {
    private final UploadService uploadService;

    @GetMapping("/upload-test")
    public String uploadTestForm() {
        return "/upload-test/upload-test";
    }
    @GetMapping("/upload-test/{orderNo}")
    public String uploadTestFormWithImage(@PathVariable("orderNo") Long orderNo, Model model) {
        List<UploadImage> images = uploadService.findImages(UploadImage.builder()
                .orderNo(orderNo)
                .build());
        model.addAttribute("images", images);
        return "/upload-test/upload-test";
    }
    @PostMapping("/upload-test")
    public String uploadTestPost(@RequestParam("image") MultipartFile image, @RequestParam("orderNo") Long orderNo) throws IOException {
        UploadImageDto uploadImageDto = UploadImageDto.builder()
                .image(image)
                .orderNo(orderNo)
                .build();
        uploadService.insertImage(uploadImageDto);
        return "redirect:/upload-test/" + orderNo;
    }
}
