package com.o2.site.member.dto;

import com.o2.site.member.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDTO {
    private String id;
    private String password;
    private String passwordCheck;
    private String name;
    private String phoneNumber;
    private String address;
    private MultipartFile image;
}
