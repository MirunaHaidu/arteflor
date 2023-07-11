package com.demo.arteflor.service.cart;


import com.demo.arteflor.dto.cart.CartDto;
import com.demo.arteflor.model.cart.Cart;

public interface CartService {

    Cart addOrnamentToCart(Integer cartId, Integer ornamentId, Integer quantity);
    String removeFromCart(Integer cartId, Integer ornamentId);
}
