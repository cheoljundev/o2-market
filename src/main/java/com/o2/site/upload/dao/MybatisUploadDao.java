package com.o2.site.upload.dao;

import com.o2.site.upload.domain.UploadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisUploadDao implements UploadDao{
    private final UploadMapper uploadMapper;
    @Override
    public void insertImage(UploadImage uploadImage) {
        uploadMapper.insertImage(uploadImage);
    }

    @Override
    public void updateImage(UploadImage uploadImage) {
        uploadMapper.updateImage(uploadImage);
    }

    @Override
    public void deleteImage(UploadImage uploadImage) {
        uploadMapper.deleteImage(uploadImage);
    }

    @Override
    public UploadImage findByImageNo(Long imageNo) {
        return uploadMapper.findByImageNo(imageNo);
    }

    @Override
    public UploadImage findImage(UploadImage uploadImage) {
        return uploadMapper.findImage(uploadImage);
    }

    @Override
    public List<UploadImage> findImages(UploadImage uploadImage) {
        return uploadMapper.findImages(uploadImage);
    }
}
