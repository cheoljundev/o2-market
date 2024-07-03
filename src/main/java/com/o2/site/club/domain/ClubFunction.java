package com.o2.site.club.domain;

import com.o2.site.member.dto.CustomUserDetails;
import org.springframework.ui.Model;

public class ClubFunction {

    public static long getUserNo(CustomUserDetails user, Model model) {
        long loginUserNo = 0;
        if (user != null) {
            loginUserNo = user.getUser().getMemberRoles().get(0).getMemberNo();
            model.addAttribute("loginUserNo", loginUserNo);
        }

        return loginUserNo;
    }
}
