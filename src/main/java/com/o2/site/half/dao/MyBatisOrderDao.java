package com.o2.site.half.dao;

import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.order.*;
import com.o2.site.upload.domain.UploadImage;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderDao implements OrderDao{

    private final OrderMapper orderMapper;
    private final UploadService uploadService;

    @Override
    public AdminOrderDetailDto findByOrderNo(Long orderNo) {
        return orderMapper.findByOrderNo(orderNo);
    }


    @Override
    public List<AdminOrderListDto> findRange(int start, int end, SearchCond searchCond) {
        List<AdminOrderListDto> adminOrderListDtoList = new ArrayList<>();
        orderMapper.findRange(start, end, searchCond).forEach(adminOrderJoinListDto -> {
            UploadImage image = uploadService.findImages(UploadImageDto.builder()
                    .orderNo(adminOrderJoinListDto.getOrderNo())
                    .build()).get(0);

            adminOrderListDtoList.add(AdminOrderListDto.builder()
                    .orderNo(adminOrderJoinListDto.getOrderNo())
                    .createAt(adminOrderJoinListDto.getCreateAt())
                    .image(image)
                    .title(adminOrderJoinListDto.getTitle())
                    .recipientName(adminOrderJoinListDto.getRecipientName())

                    .price(adminOrderJoinListDto.getPrice() / 2)
                    .state(adminOrderJoinListDto.getState())
                    .build());
        });
        return adminOrderListDtoList;
    }

    @Override
    public void insertOrder(InsertOrderDto insertOrderDto) {
        orderMapper.insertOrder(insertOrderDto);
    }

    @Override
    public void updateOrder(UpdateOrderDto updateOrderDto) {
        orderMapper.updateOrder(updateOrderDto);
    }

    @Override
    public void deleteOrder(Long orderNo) {
        orderMapper.deleteOrder(orderNo);
    }

    @Override
    public int findPages(SearchCond searchCond, int pageSIze) {
        int count = orderMapper.count(searchCond);
        return (int) Math.ceil((double) count / pageSIze); // 페이지 수 계산, Math.ceil()은 올림 함수, Math.floor()는 내림 함수, Math.round()는 반올림 함수
    }
}
