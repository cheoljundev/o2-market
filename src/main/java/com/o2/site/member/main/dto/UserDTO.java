package com.o2.site.member.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private int Member_No;
    private String id;
    private String password;
    private String password_Check;
    private String name;
    private String Phone_Number;
    private String address;
    private String image;
    private int mileage;

    public UserDTO() {

    }

    public UserDTO(String id, String password, String password_Check, String name, String phone_Number, String address) {
        this.id = id;
        this.password = password;
        this.password_Check = password_Check;
        this.name = name;
        Phone_Number = phone_Number;
        this.address = address;
    }
}