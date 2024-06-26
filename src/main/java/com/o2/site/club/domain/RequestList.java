package com.o2.site.club.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Builder
@Data
public class RequestList<T> {

    private T data;
    private Pageable pageable;
}
