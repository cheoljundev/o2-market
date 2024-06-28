package com.o2.site.upload.dao;

import com.o2.site.config.O2Application;
import com.o2.site.upload.domain.UploadImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class UploadMapperTest {

    @Autowired
    private UploadMapper uploadMapper;

    @Test
    void insertImage() {
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());
    }

    @Test
    void updateImage() {
        uploadMapper.insertImage(UploadImage.builder()
                .imageNo(99L)
                .imageName("test.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());

        uploadMapper.updateImage(UploadImage.builder()
                .imageNo(99L)
                .imageName("test2.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());

        UploadImage uploadImage = uploadMapper.findByImageNo(99L);
        Assertions.assertThat(uploadImage.getImageName()).isEqualTo("test2.jpg");
    }

    @Test
    void deleteImage() {
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test1.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test2.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test3.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());

        uploadMapper.deleteImage(UploadImage.builder()
                .orderNo(1L)
                .build());
        List<UploadImage> images = uploadMapper.findImages(UploadImage.builder()
                .orderNo(1L)
                .build());
        Assertions.assertThat(images.size()).isEqualTo(0);
    }

    @Test
    void findByImageNo() {
        Long imageNo = 1L;
        uploadMapper.insertImage(UploadImage.builder()
                .imageNo(imageNo)
                .imageName("test.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());

        UploadImage uploadImage = uploadMapper.findByImageNo(imageNo);
        Assertions.assertThat(uploadImage.getImageNo()).isEqualTo(imageNo);
    }

    @Test
    void findImage() {
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(1L)
                .build());
        Long orderNo = 1L;
        UploadImage uploadImage = uploadMapper.findImage(UploadImage.builder()
                .orderNo(orderNo)
                .build());
        Assertions.assertThat(uploadImage.getOrderNo()).isEqualTo(orderNo);
    }

    @Test
    void findImages() {
        Long orderNo = 1L;
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test1.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(orderNo)
                .build());
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test2.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(orderNo)
                .build());
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test3.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(orderNo)
                .build());
        uploadMapper.insertImage(UploadImage.builder()
                .imageName("test4.jpg")
                .storedImageName(UUID.randomUUID().toString() + ".jpg")
                .orderNo(orderNo)
                .build());
        List<UploadImage> images = uploadMapper.findImages(UploadImage.builder()
                .orderNo(orderNo)
                .build());
        Assertions.assertThat(images.size()).isEqualTo(4);
    }
}