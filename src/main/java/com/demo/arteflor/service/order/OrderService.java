package com.demo.arteflor.service.order;

import com.demo.arteflor.dto.order.OrderDto;
import com.demo.arteflor.model.order.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Integer cartId, String paymentMethod);
    Order getById(Integer id);
    List<OrderDto> getAllOrders();
    List<Order> getOrdersByUserEmail(String email);
}
