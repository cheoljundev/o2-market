package com.o2.site.upload.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadImage {
    private Long imageNo;
    private String imageName;
    private String storedImageName;
    private Long orderNo;
    private Long tradeNo;
    private Long memberNo;
    private Long advNo;
    private Long clubBoardId;
}
