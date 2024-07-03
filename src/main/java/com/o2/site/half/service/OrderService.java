package com.o2.site.half.service;

import com.o2.site.half.dao.OrderDao;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.order.*;
import com.o2.site.half.dto.product.ProductState;
import com.o2.site.half.dto.product.UpdateProductDto;
import com.o2.site.member.dao.MemberMapper;
import com.o2.site.member.domain.Member;
import com.o2.site.member.dto.CustomUserDetails;
import com.o2.site.trade.service.TradeService;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final ProductService productService;
    private final MemberMapper memberMapper;
    private final UploadService uploadService;

    public AdminOrderDetailDto findByOrderNo(Long orderNo){
        return orderDao.findByOrderNo(orderNo);
    }

    public AdminOrderDetailDto findByProductNo(Long productNo){
        return orderDao.findByProductNo(productNo);
    }
    public List<AdminOrderListDto>  findRange(int start, int end, com.o2.site.half.dao.SearchCond searchCond){
        return orderDao.findRange(start, end, searchCond);
    }
    public void insertOrder(InsertOrderDto insertOrderDto, CustomUserDetails customUserDetails) throws IOException {
        insertOrderDto.setBuyerMemberNo(customUserDetails.getUser().getMemberNo());

        Long price = productService.findByProductNo(insertOrderDto.getProductNo()).getPrice();
        Integer buyerMileage = memberMapper.findByMemberNo(insertOrderDto.getBuyerMemberNo()).getMileage();
        if (!insertOrderDto.getMileage().equals(price)){
            throw new IllegalArgumentException("마일리지 금액이 상품 금액과 일치하지 않습니다.");
        }
        if (buyerMileage < insertOrderDto.getMileage()){
            throw new IllegalArgumentException("마일리지가 부족합니다.");
        }

        memberMapper.updateMember(Member.builder()
                .memberNo(insertOrderDto.getBuyerMemberNo())
                .mileage((int) (buyerMileage - insertOrderDto.getMileage()))
                .build());

        customUserDetails.getUser().setMileage((int) (buyerMileage - insertOrderDto.getMileage()));
        orderDao.insertOrder(insertOrderDto);
        productService.updateProduct(UpdateProductDto.builder()
                .productNo(insertOrderDto.getProductNo())
                .state(ProductState.SOLD)
                .build());
        Long tradeNo = productService.findByProductNo(insertOrderDto.getProductNo()).getTradeNo();
        UploadImage uploadImage = uploadService.findImages(UploadImageDto.builder()
                .tradeNo(tradeNo)
                .build()).get(0);
        Long orderNo = findByProductNo(insertOrderDto.getProductNo()).getOrderNo();
        log.info("orderNo: {}", orderNo);
        uploadService.duplicateImage(uploadImage, UploadImageDto.builder()
                .orderNo(orderNo)
                .build());
    }
    public void updateOrder(UpdateOrderDto updateOrderDto){
        orderDao.updateOrder(updateOrderDto);
    }
    public void deleteOrder(Long orderNo){
        orderDao.deleteOrder(orderNo);
    }

    public int findPages(com.o2.site.half.dao.SearchCond searchCond, int pageSize){
        return orderDao.findPages(searchCond, pageSize);
    }

    public List<SearchCond> getSearchCond(){
        List<SearchCond> searchConds = new ArrayList<>();
        searchConds.add(OrderService.SearchCond.builder().field("buyerMemberId").name("구매자 아이디").build());
        searchConds.add(OrderService.SearchCond.builder().field("buyerPhone").name("구매자 전화번호").build());
        searchConds.add(OrderService.SearchCond.builder().field("recipientName").name("수령자 이름").build());
        searchConds.add(OrderService.SearchCond.builder().field("recipientPhone").name("수령자 전화번호").build());
        return searchConds;
    }

    @Data
    @Builder
    public static class SearchCond {
        private String field;
        private String name;
    }
}
