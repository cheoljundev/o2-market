package com.o2.site.upload.dao;

import com.o2.site.upload.domain.UploadImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper {
    void insertImage(UploadImage uploadImage);
    void updateImage(UploadImage uploadImage);
    void deleteImage(UploadImage uploadImage);
    UploadImage findByImageNo(Long imageNo);
    UploadImage findImage(UploadImage uploadImage);
    List<UploadImage> findImages(UploadImage uploadImage);
}
