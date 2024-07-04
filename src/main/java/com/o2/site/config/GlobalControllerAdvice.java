package com.o2.site.config;

import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.domain.Member;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.member.dto.MemberDTO;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UploadService uploadService;
    private final MemberMapper memberMapper;

    @ModelAttribute("user")
    public MemberDTO loginUser(@AuthenticationPrincipal CustomUserDetails user) {
        if (user == null) {
            return null;
        }
        return user.getUser();
    }

    @ModelAttribute("profile")
    public UploadImage profile(@AuthenticationPrincipal CustomUserDetails user) {
        if (user == null) {
            return null;
        }
        String id = user.getUser().getId();
        Long memberNo = memberMapper.findByUsername(id).getMemberNo();
        return uploadService.findImages(UploadImageDto.builder()
                .memberNo(memberNo)
                .build()).get(0);
    }

    @ModelAttribute("uri")
    public String currentUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getRequestURI();
    }
}
