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
import com.demo.arteflor.service.cart.CartService;
import com.demo.arteflor.service.order.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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


    public OrderServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository, CartRepository cartRepository, OrderOrnamentRepository orderOrnamentRepository, CartService cartService) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderOrnamentRepository = orderOrnamentRepository;
        this.cartService = cartService;
    }

    @Override
    public Order placeOrder(OrderDto orderDto, String email, Integer cartId) {
        Order order = OrderConvertor.convertDtoToEntity(orderDto);
        Payment payment = paymentRepository.getById(orderDto.getPaymentId());
        order.setPayment(payment);

        Cart cart = cartRepository.findCartByEmailAndId(email, cartId);
        if (cart == null) {
            throw new ResourceNotFoundException("cart", "id", String.valueOf(cartId));
        }

        List<CartOrnament> cartOrnaments = cart.getCartOrnaments();
        if (cartOrnaments.size() == 0) {
            throw new APIException("Cart is empty");
        }

        List<OrderOrnament> orderOrnaments = new ArrayList<>();
        Order savedOrder = orderRepository.save(order);

        for (CartOrnament cartOrnament:cartOrnaments){
            OrderOrnament orderOrnament = new OrderOrnament();
            orderOrnament.setOrnament(cartOrnament.getOrnament());
            orderOrnament.setQuantity(cartOrnament.getQuantity());
            orderOrnament.setPrice(cartOrnament.getOrnamentPrice());
            orderOrnament.setOrder(savedOrder);

            orderOrnaments.add(orderOrnament);
        }

        orderOrnaments = orderOrnamentRepository.saveAll(orderOrnaments);

        cart.getCartOrnaments().forEach(item->{
            int quantity = item.getQuantity();
            Ornament ornament = item.getOrnament();
            cartService.removeFromCart(cartId, item.getOrnament().getId());
            ornament.setQuantity(ornament.getQuantity()-quantity);
                }
        );


        return orderRepository.save(order);
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
