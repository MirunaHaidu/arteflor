package com.demo.arteflor.service.order.impl;

import com.demo.arteflor.convertor.order.OrderConvertor;
import com.demo.arteflor.convertor.order.OrderOrnamentConvertor;
import com.demo.arteflor.dto.order.OrderOrnamentDto;
import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.model.order.OrderOrnament;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.repository.order.OrderOrnamentRepository;
import com.demo.arteflor.repository.order.OrderRepository;
import com.demo.arteflor.repository.ornament.OrnamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("test_qualifier_orderOrnamentServiceImpl")
@Transactional
public class OrderOrnamentServiceImpl {
    private final OrderOrnamentRepository orderOrnamentRepository;
    private final OrnamentRepository ornamentRepository;
    private final OrderRepository orderRepository;

    public OrderOrnamentServiceImpl(OrderOrnamentRepository orderOrnamentRepository, OrnamentRepository ornamentRepository, OrderRepository orderRepository) {
        this.orderOrnamentRepository = orderOrnamentRepository;
        this.ornamentRepository = ornamentRepository;
        this.orderRepository = orderRepository;
    }

    public OrderOrnament addOrderedOrnament(OrderOrnamentDto orderOrnamentDto){
        OrderOrnament orderOrnament = OrderOrnamentConvertor.convertDtoToEntity(orderOrnamentDto);
        Ornament ornament = ornamentRepository.getById(orderOrnamentDto.getOrnamentId());
        Order order = orderRepository.getById(orderOrnamentDto.getOrderId());

        orderOrnament.setOrnament(ornament);
        orderOrnament.setOrder(order);

        return this.orderOrnamentRepository.save(orderOrnament);
    }
}
