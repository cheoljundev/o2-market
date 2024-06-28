package com.o2.site.upload.controller;

import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String uploadTestFormWithImage(Model model) {
        List<UploadImage> images = uploadService.findImages(UploadImageDto.builder()
                .tradeNo(3L)
                .build());
        model.addAttribute("images", images);
        return "/upload-test/upload-test";
    }
    @PostMapping("/upload-test")
    public String uploadTestPost(@RequestParam("image") MultipartFile image) throws IOException {
        UploadImageDto uploadImageDto = UploadImageDto.builder()
                .image(image)
                .tradeNo(3L)
                .build();
        uploadService.insertImage(uploadImageDto);
        return "redirect:/upload-test/" + "1";
    }

    @PostMapping("/update-test")
    public String update(@RequestParam("image") MultipartFile image) throws IOException {
        UploadImageDto uploadImageDto = UploadImageDto.builder()
                .image(image)
                .tradeNo(3L)
                .build();

        Long imageNo = uploadService.findImages(UploadImageDto.builder()
                .tradeNo(3L)
                .build()).get(0).getImageNo();

        uploadService.updateImage(imageNo, uploadImageDto);
        return "redirect:/upload-test/" + "1";
    }

    @PostMapping("/delete-test")
    public String delete() throws IOException {
        UploadImageDto uploadImageDto = UploadImageDto.builder()
                .orderNo(1L)
                .build();

        uploadService.deleteImage(uploadImageDto);
        return "redirect:/upload-test/" + "1";
    }

    @PostMapping("/duplicate-test")
    public String duplicate() throws IOException {
        UploadImage oldImage = uploadService.findImages(UploadImageDto.builder()
                .orderNo(1L)
                .build()).get(0);
        UploadImageDto uploadImageDto = UploadImageDto.builder()
                        .tradeNo(1L)
                        .build();

        uploadService.duplicateImage(oldImage, uploadImageDto);
        return "redirect:/upload-test/" + "1";
    }
}
