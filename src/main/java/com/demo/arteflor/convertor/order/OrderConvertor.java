package com.demo.arteflor.convertor.order;

import com.demo.arteflor.dto.order.OrderDto;
import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.repository.order.OrderRepository;
import org.aspectj.weaver.ast.Or;

public class OrderConvertor {
    private final OrderRepository orderRepository;

    public OrderConvertor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static Order convertDtoToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setEmail(orderDto.getEmail());
//        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderStatus("Order accepted!");
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderDate(orderDto.getOrderDate());

        return order;
    }

    public static OrderDto convertEntityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setEmail(order.getEmail());
        orderDto.setOrderStatus("Order accepted!");
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderDate(order.getOrderDate());

        return orderDto;
    }
}
