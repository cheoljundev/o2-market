package com.o2.site.club.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubBoardImgDto {
    private long boardImgId;
    private long boardId;
    private String image;
}
