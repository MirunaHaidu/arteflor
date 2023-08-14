package com.demo.arteflor.service.cart;


import com.demo.arteflor.dto.cart.CartOrnamentDto;
import com.demo.arteflor.model.cart.Cart;

import java.util.List;

public interface CartService {

    Cart addOrnamentToCart(CartOrnamentDto cartOrnamentDto);
    String removeFromCart(Integer cartId, Integer ornamentId);
    List<Cart> getAllCarts();
    Cart getCartById(Integer cartId);
    Cart updateCartOrnament(Integer cartId, Integer ornamentId, Integer quantity);
}
