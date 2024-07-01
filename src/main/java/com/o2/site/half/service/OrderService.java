package com.o2.site.half.service;

import com.o2.site.half.dao.OrderDao;
import com.o2.site.half.dao.OrderSearchCond;
import com.o2.site.half.domain.Order;
import com.o2.site.half.dto.AdminOrderDetailDto;
import com.o2.site.half.dto.AdminOrderListDto;
import com.o2.site.half.dto.InsertOrderDto;
import com.o2.site.half.dto.UpdateOrderDto;
import com.o2.site.upload.dto.UploadImageDto;
import com.o2.site.upload.service.UploadService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final UploadService uploadService;

    public List<AdminOrderListDto> findAll(OrderSearchCond orderSearchCond){
        List<AdminOrderListDto> orders = new ArrayList<>();
        orderDao.findAll(orderSearchCond).stream().forEach((order -> {
            orders.add(AdminOrderListDto.builder()
                    .orderNo(order.getOrderNo())
                    .createAt(order.getCreateAt())
                    .image(uploadService.findImage(UploadImageDto.builder()
                            .orderNo(order.getOrderNo())
                            .build()
                    ))
                    .title(order.getTitle())
                    .recipientName(order.getRecipientName())
                    .halfPrice(order.getHalfPrice())
                    .stateName(order.getState())
                    .build());
        }));
        return orders;
    }
    public AdminOrderDetailDto findByOrderNo(Long orderNo){
        Order order = orderDao.findByOrderNo(orderNo);

        if (order == null) {
            return null;
        }

        return AdminOrderDetailDto.builder()
                    .orderNo(order.getOrderNo())
                    .createAt(order.getCreateAt())
                    .title(order.getTitle())
                    .recipientName(order.getRecipientName())
                    .recipientPhone(order.getRecipientPhone())
                    .recipientAddress(order.getRecipientAddress())
                    .deliveryMemo(order.getDeliveryMemo())
                    .invoice(order.getInvoice())
                    .build();
    }
    public List<AdminOrderListDto>  findRange(int start, int end, OrderSearchCond orderSearchCond){
        List<AdminOrderListDto> orders = new ArrayList<>();
        orderDao.findRange(start, end, orderSearchCond).stream().forEach((order -> {
            orders.add(AdminOrderListDto.builder()
                    .orderNo(order.getOrderNo())
                    .createAt(order.getCreateAt())
                    .image(uploadService.findImage(UploadImageDto.builder()
                            .orderNo(order.getOrderNo())
                            .build()
                    ))
                    .title(order.getTitle())
                    .recipientName(order.getRecipientName())
                    .halfPrice(order.getHalfPrice())
                    .stateName(order.getState())
                    .build());
        }));
        return orders;
    }
    public void insertOrder(InsertOrderDto insertOrderDto){
        orderDao.insertOrder(insertOrderDto);
    }
    public void updateOrder(UpdateOrderDto updateOrderDto){
        orderDao.updateOrder(updateOrderDto);
    }
    public void deleteOrder(Long orderNo){
        orderDao.deleteOrder(orderNo);
    }

    public int findPages(OrderSearchCond orderSearchCond, int pageSize){
        return orderDao.findPages(orderSearchCond, pageSize);
    }

    public List<SearchCond> getSearchCond(){
        List<SearchCond> searchConds = new ArrayList<>();
        searchConds.add(SearchCond.builder().field("buyerMemberId").name("구매자 아이디").build());
        searchConds.add(SearchCond.builder().field("buyerPhone").name("구매자 전화번호").build());
        searchConds.add(SearchCond.builder().field("recipientName").name("수령자 이름").build());
        searchConds.add(SearchCond.builder().field("recipientPhone").name("수령자 전화번호").build());
        return searchConds;
    }

    @Data
    @Builder
    public static class SearchCond {
        private String field;
        private String name;
    }
}
