package com.o2.site.member.dao;

import com.o2.site.config.O2Application;
import com.o2.site.member.domain.Member;
import com.o2.site.member.domain.MemberRole;
import com.o2.site.member.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = O2Application.class)
@Transactional
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void insertMember() {
        memberMapper.insertMember(Member
                .builder()
                        .id("Test2222")
                        .password("1234")
                        .name("멀티캠퍼스2222")
                        .phoneNumber("55557777")
                        .address("서울")
                .build());
        Member member = memberMapper.findByUsername("Test2222");
        Assertions.assertThat(member.getId()).isEqualTo("Test2222");
    }

    @Test
    void findByUsername() {
        memberMapper.insertMember(Member
                .builder()
                .id("Test2222")
                .password("1234")
                .name("멀티캠퍼스2222")
                .phoneNumber("55557777")
                .address("서울")
                .build());
        Member member = memberMapper.findByUsername("Test2222");
        Assertions.assertThat(member.getId()).isEqualTo("Test2222");
    }

    @Test
    void insertMemberRole() {
        memberMapper.insertMember(Member
                .builder()
                .id("Test2222")
                .password("1234")
                .name("멀티캠퍼스2222")
                .phoneNumber("55557777")
                .address("서울")
                .build());
        Member member = memberMapper.findByUsername("Test2222");

        Long memberNo = member.getMemberNo();
        memberMapper.insertMemberRole(MemberRole
                .builder()
                .memberNo(memberNo)
                        .role("ROLE_USER")
                .build());
        int size = memberMapper.findMemberRole(memberNo).size();
        Assertions.assertThat(size).isEqualTo(1);
    }
}