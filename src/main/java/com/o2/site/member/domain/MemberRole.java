package com.o2.site.member.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class MemberRole {
    private Long memberNo;
    private String role;
}
