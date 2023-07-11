package com.demo.arteflor.service.cart;


import com.demo.arteflor.dto.cart.CartDto;
import com.demo.arteflor.dto.cart.CartOrnamentDto;
import com.demo.arteflor.model.cart.Cart;

public interface CartService {

    Cart addOrnamentToCart(CartOrnamentDto cartOrnamentDto);
    String removeFromCart(Integer cartId, Integer ornamentId);
}
