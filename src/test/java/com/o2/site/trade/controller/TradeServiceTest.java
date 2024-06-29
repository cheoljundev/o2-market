package com.o2.site.trade.controller;

import com.o2.site.config.O2Application;
import com.o2.site.trade.domain.AdvDomain;
import com.o2.site.trade.dto.AdvDetailDto;
import com.o2.site.trade.dto.AdvListDto;
import com.o2.site.trade.dto.TradeMainDto;
import com.o2.site.trade.service.TradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = O2Application.class)
@Transactional
class TradeServiceTest {
    @Autowired
    TradeService tradeService;
    //페이징 테스트
    @Test
    void trade_main() {
        //given
        int page=0;
        int PAGE_SIZE=5;

        //when
        ArrayList<TradeMainDto> mainlist = tradeService.selectMainList();
        ArrayList<AdvListDto> advList = tradeService.getAdvList();
        List<TradeMainDto> paginatedMainList = paginate(mainlist, page, PAGE_SIZE);

        Random random = new Random();
        int rand=random.nextInt(advList.size());
        AdvListDto firstAdv = advList.isEmpty() ? null : advList.get(rand);

        int totalPages = (int) Math.ceil((double) mainlist.size() / PAGE_SIZE);

        //then
        assertThat(totalPages+1).isEqualTo(paginatedMainList.size());

    }

    private List<TradeMainDto> paginate(List<TradeMainDto> list, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return list.subList(start, end);
    }
    //상품 등록 테스트
    @Test
    void insertAdv() {
//        //given
//        AdvDomain advDomain = AdvDomain.builder()
//                .title("title")
//                .advName("name")
//                .companyName("naver")
//                .companyLink("http://www.naver.com")
//                .content("naver content")
//                .build();
//        //when
//        tradeService.insertAdv(advDomain);
//        //then
//        assertThat(advDomain.getAdvNo()).isEqualTo(7);
    }

    //광고 디테일 테스트
    @Test
    void trade_adv_detail() {
        //given
        AdvDetailDto advDetailDto= AdvDetailDto.builder()
                .advNo(1)
                .build();
        //when
        AdvDetailDto test = tradeService.getAdvDetail("1");
        //then
        assertThat(advDetailDto.getAdvNo()).isEqualTo(test.getAdvNo());
    }

    @Test
    void deleteAdv() {
        //given
        String advNo="1";
        //when
        tradeService.deleteAdv("1");
        ArrayList<AdvListDto> list = tradeService.getAdvList();
        //then
        assertThat(list.get(0).getAdvNo()).isNotEqualTo(1);
    }
}