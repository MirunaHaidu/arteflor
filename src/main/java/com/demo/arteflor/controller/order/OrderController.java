package com.demo.arteflor.controller.order;

import com.demo.arteflor.dto.order.OrderDto;
import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.service.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/order")
@ControllerAdvice
public class OrderController {
    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orderOrnaments")
    public ResponseEntity<Order> orderOrnaments(@RequestBody @Valid OrderDto orderDto, String email, Integer cartId){
        return ResponseEntity.ok(orderService.placeOrder(orderDto, email, cartId));
    }

}
