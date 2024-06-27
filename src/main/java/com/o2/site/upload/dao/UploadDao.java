package com.o2.site.upload.dao;

import com.o2.site.upload.domain.UploadImage;

import java.util.List;

public interface UploadDao {
    void insertImage(UploadImage uploadImage);
    void updateImage(UploadImage uploadImage);
    void deleteImage(UploadImage uploadImage);
    UploadImage findByImageNo(Long imageNo);
    UploadImage findImage(UploadImage uploadImage);
    List<UploadImage> findImages(UploadImage uploadImage);
}
