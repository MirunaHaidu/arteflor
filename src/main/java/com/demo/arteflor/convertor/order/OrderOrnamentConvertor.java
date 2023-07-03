package com.demo.arteflor.convertor.order;

import com.demo.arteflor.dto.order.OrderOrnamentDto;
import com.demo.arteflor.model.order.OrderOrnament;
import com.demo.arteflor.repository.order.OrderOrnamentRepository;

public class OrderOrnamentConvertor {
    private final OrderOrnamentRepository orderOrnamentRepository;

    public OrderOrnamentConvertor(OrderOrnamentRepository orderOrnamentRepository) {
        this.orderOrnamentRepository = orderOrnamentRepository;
    }

    public static OrderOrnament convertDtoToEntity(OrderOrnamentDto orderOrnamentDto){
        OrderOrnament orderOrnament = new OrderOrnament();
        orderOrnament.setPrice(orderOrnamentDto.getPrice());
        orderOrnament.setQuantity(orderOrnamentDto.getQuantity());

        return orderOrnament;
    }

    public static OrderOrnamentDto convertEntityToDto(OrderOrnament orderOrnament){
        OrderOrnamentDto orderOrnamentDto = new OrderOrnamentDto();
        orderOrnamentDto.setPrice(orderOrnament.getPrice());
        orderOrnamentDto.setQuantity(orderOrnament.getQuantity());

        return orderOrnamentDto;
    }
}
