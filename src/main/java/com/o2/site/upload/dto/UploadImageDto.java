package com.o2.site.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageDto {
    MultipartFile image;
    private Long orderNo;
    private Long tradeNo;
    private Long memberNo;
    private Long advNo;
    private Long clubBoardId;
    private String clubName;
}
