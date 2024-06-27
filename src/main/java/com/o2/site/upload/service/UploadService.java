package com.o2.site.upload.service;

import com.o2.site.upload.dao.UploadDao;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final UploadDao uploadDao;
    String projectPath = System.getProperty("user.dir");
    @Value("${file.dir}") String fileDir;

    public void insertImage(UploadImageDto uploadImageDto) throws IOException {
        UploadImage uploadImage = createUploadImage(uploadImageDto);
        uploadDao.insertImage(uploadImage);

        if (uploadImageDto.getImage() != null) {
            saveFile(uploadImageDto.getImage(), uploadImage.getStoredImageName());
        }
    }

    public void updateImage(Long ImageNo, UploadImageDto uploadImageDto){
        UploadImage uploadImage = findByImageNo(ImageNo);
        String oldStoredImageName = uploadImage.getStoredImageName();
        UploadImage createImage = createUploadImage(uploadImageDto);
        uploadImage.setImageName(createImage.getImageName());
        uploadImage.setStoredImageName(createImage.getStoredImageName());
        uploadImage.setOrderNo(createImage.getOrderNo());
        uploadImage.setTradeNo(createImage.getTradeNo());
        uploadImage.setMemberNo(createImage.getMemberNo());
        uploadImage.setAdvNo(createImage.getAdvNo());
        uploadImage.setClubBoardId(createImage.getClubBoardId());

        uploadDao.updateImage(uploadImage);

        if (uploadImageDto.getImage() != null) {
            try {
                saveFile(uploadImageDto.getImage(), uploadImage.getStoredImageName());
                deleteFile(oldStoredImageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteImage(UploadImageDto uploadImageDto){
        UploadImage deleteImage = UploadImage.builder()
                .orderNo(uploadImageDto.getOrderNo())
                .tradeNo(uploadImageDto.getTradeNo())
                .memberNo(uploadImageDto.getMemberNo())
                .advNo(uploadImageDto.getAdvNo())
                .clubBoardId(uploadImageDto.getClubBoardId())
                .build();

        List<UploadImage> images = findImages(uploadImageDto);
        images.forEach(image -> {
            try {
                deleteFile(image.getStoredImageName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        uploadDao.deleteImage(deleteImage);
    }
    public UploadImage findByImageNo(Long imageNo){
        return uploadDao.findByImageNo(imageNo);
    }
    public UploadImage findImage(UploadImageDto uploadImageDto){
        return uploadDao.findImage(UploadImage.builder()
                .orderNo(uploadImageDto.getOrderNo())
                .tradeNo(uploadImageDto.getTradeNo())
                .memberNo(uploadImageDto.getMemberNo())
                .advNo(uploadImageDto.getAdvNo())
                .clubBoardId(uploadImageDto.getClubBoardId())
                .build()
                );
    }
    public List<UploadImage> findImages(UploadImageDto uploadImageDto){
        return uploadDao.findImages(UploadImage.builder()
                .orderNo(uploadImageDto.getOrderNo())
                .tradeNo(uploadImageDto.getTradeNo())
                .memberNo(uploadImageDto.getMemberNo())
                .advNo(uploadImageDto.getAdvNo())
                .clubBoardId(uploadImageDto.getClubBoardId())
                .build());
    }

    private UploadImage createUploadImage(UploadImageDto uploadImageDto) {
        MultipartFile image = uploadImageDto.getImage();
        String fileName = null;
        String storeFileName = null;

        if (image != null) {
            fileName = image.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            int extIndex = fileName.lastIndexOf(".") + 1;
            String ext = fileName.substring(extIndex);
            storeFileName = uuid + "." + ext;
        }

        return UploadImage.builder()
                .imageName(fileName)
                .storedImageName(storeFileName)
                .orderNo(uploadImageDto.getOrderNo())
                .tradeNo(uploadImageDto.getTradeNo())
                .memberNo(uploadImageDto.getMemberNo())
                .advNo(uploadImageDto.getAdvNo())
                .clubBoardId(uploadImageDto.getClubBoardId())
                .build();
    }

    private void saveFile(MultipartFile image, String storeFileName) throws IOException {
        // 파일 저장 경로
        String filePath = projectPath + fileDir + storeFileName;

        // 파일 디렉토리가 없으면 생성
        File directory = new File(projectPath + fileDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        image.transferTo(new File(filePath));
    }

    private void deleteFile(String storeFileName) throws IOException {
        if (storeFileName != null) {
            Path filePath = Paths.get(projectPath + fileDir + storeFileName);
            Files.deleteIfExists(filePath);
        }
    }

}
