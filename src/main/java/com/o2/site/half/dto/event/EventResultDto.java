package com.o2.site.half.dto.event;

import lombok.Data;

import java.util.List;

@Data
public class EventResultDto {
    private List<String> categoryCodes;
    private int number;
}
