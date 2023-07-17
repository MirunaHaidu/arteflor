package com.demo.arteflor.service.order.impl;

import com.demo.arteflor.convertor.order.OrderConvertor;
import com.demo.arteflor.dto.order.OrderDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.cart.CartOrnament;
import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.model.order.OrderOrnament;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.model.user.Payment;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.order.OrderOrnamentRepository;
import com.demo.arteflor.repository.order.OrderRepository;
import com.demo.arteflor.repository.user.PaymentRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.cart.CartService;
import com.demo.arteflor.service.order.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderOrnamentRepository orderOrnamentRepository;
    private final CartService cartService;
    private final UserRepository userRepository;


    public OrderServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository, CartRepository cartRepository, OrderOrnamentRepository orderOrnamentRepository, CartService cartService, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderOrnamentRepository = orderOrnamentRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder(Integer cartId, String paymentMethod) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", String.valueOf(cartId)));


        Order order = new Order();
        order.setEmail(cart.getUser().getEmail());
        order.setOrderDateTime(LocalDateTime.now());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderStatus("Order accepted!");

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);
        payment = paymentRepository.save(payment);

        order.setPayment(payment);

        Order savedOrder = orderRepository.save(order);

        List<CartOrnament> cartOrnaments = cart.getCartOrnaments();

        if (cartOrnaments.size() == 0) {
            throw new APIException("Cart is empty");
        }

        List<OrderOrnament> orderOrnaments = new ArrayList<>();
        for(CartOrnament cartOrnament:cartOrnaments){
            OrderOrnament orderOrnament = new OrderOrnament();

            orderOrnament.setOrnament(cartOrnament.getOrnament());
            orderOrnament.setQuantity(cartOrnament.getQuantity());
            orderOrnament.setPrice(cartOrnament.getOrnamentPrice());
            orderOrnament.setOrder(savedOrder);

            orderOrnaments.add(orderOrnament);
        }

        orderOrnaments = orderOrnamentRepository.saveAll(orderOrnaments);

        cart.getCartOrnaments().forEach(item -> {
            int quantity = item.getQuantity();
            Ornament ornament = item.getOrnament();
            cartService.removeFromCart(cart.getCartId(), item.getOrnament().getId());
            ornament.setQuantity(ornament.getQuantity()-quantity);
        });

        savedOrder.setOrderOrnaments(orderOrnaments);

        return savedOrder;
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return this.orderRepository.findAll().stream()
                .map(OrderConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderByUserEmail() {
        return null;
    }
}
