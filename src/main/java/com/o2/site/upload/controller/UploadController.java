package com.o2.site.upload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class UploadController {
    @Value("${file.dir}") String fileDir;
    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable("fileName") String fileName)  {
        String directory = System.getProperty("user.dir") + fileDir;
        String filePath = directory + fileName;
        File file = new File(filePath);

        Resource resource = new FileSystemResource(file); // 파일을 리소스로 변환


        // 파일 확장자를 통해 MIME 타입을 결정
        String mimeType;
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (ext) {
            case "jpg":
            case "jpeg":
                mimeType = "image/jpeg";
                break;
            case "png":
                mimeType = "image/png";
                break;
            case "gif":
                mimeType = "image/gif";
                break;
            default:
                mimeType = "application/octet-stream";  // 일반적인 바이너리 파일
        }

        // Content-Type 헤더를 설정하고 파일을 반환
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, mimeType);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
