package com.demo.arteflor.controller.order;

import com.demo.arteflor.dto.order.OrderDto;
import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.service.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
public class OrderController {
    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/public/order/orderOrnaments")
    public ResponseEntity<Order> orderOrnaments(@RequestParam Integer cartId, @RequestParam String paymentMethod){
        Order order = orderService.placeOrder(cartId, paymentMethod);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/public/order/getOrdersByEmail")
    public ResponseEntity<List<Order>> getOrdersByEmail(@RequestParam String email){
        List<Order> orders = orderService.getOrdersByUserEmail(email);

        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }



}
