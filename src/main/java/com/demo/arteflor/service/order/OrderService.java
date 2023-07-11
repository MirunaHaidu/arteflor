package com.demo.arteflor.service.order;

import com.demo.arteflor.dto.order.OrderDto;
import com.demo.arteflor.model.order.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderDto orderDto, String email, Integer cartId);
    Order getById(Integer id);
    List<OrderDto> getAllOrders();
    OrderDto getOrderByUserEmail();
}
