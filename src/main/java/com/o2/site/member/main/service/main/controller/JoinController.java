package com.o2.site.member.main.service.main.controller;

import com.o2.site.member.main.service.main.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @PostMapping("/join")
    public String register(@ModelAttribute UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);
        return "main/main";
    }




}
