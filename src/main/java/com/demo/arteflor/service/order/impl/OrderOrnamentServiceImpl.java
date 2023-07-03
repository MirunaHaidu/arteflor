package com.demo.arteflor.service.order.impl;

import com.demo.arteflor.convertor.order.OrderOrnamentConvertor;
import com.demo.arteflor.dto.order.OrderOrnamentDto;
import com.demo.arteflor.model.order.OrderOrnament;
import com.demo.arteflor.repository.order.OrderOrnamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("test_qualifier_orderOrnamentServiceImpl")
@Transactional
public class OrderOrnamentServiceImpl {
    private final OrderOrnamentRepository orderOrnamentRepository;

    public OrderOrnamentServiceImpl(OrderOrnamentRepository orderOrnamentRepository) {
        this.orderOrnamentRepository = orderOrnamentRepository;
    }

    public OrderOrnament addOrderedOrnament(OrderOrnamentDto orderOrnamentDto){
        return this.orderOrnamentRepository.save(OrderOrnamentConvertor.convertDtoToEntity(orderOrnamentDto));
    }
}
