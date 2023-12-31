package com.demo.arteflor.service.cart.impl;

import com.demo.arteflor.dto.cart.CartOrnamentDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.cart.CartOrnament;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.repository.cart.CartOrnamentRepository;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.ornament.OrnamentRepository;
import com.demo.arteflor.service.cart.CartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_cartServiceImpl")
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartOrnamentRepository cartOrnamentRepository;
    private final OrnamentRepository ornamentRepository;

    public CartServiceImpl(CartRepository cartRepository, CartOrnamentRepository cartOrnamentRepository, OrnamentRepository ornamentRepository) {
        this.cartRepository = cartRepository;
        this.cartOrnamentRepository = cartOrnamentRepository;
        this.ornamentRepository = ornamentRepository;
    }

    @Override
    public Cart addOrnamentToCart(CartOrnamentDto cartOrnamentDto) {
        Cart cart = cartRepository.findById(cartOrnamentDto.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", String.valueOf(cartOrnamentDto.getCartId())));

        Ornament ornament = ornamentRepository.getById(cartOrnamentDto.getOrnamentId());


        if (ornament.getQuantity() == 0) {
            throw new APIException(ornament.getName() + " is not available");
        }

        if (ornament.getQuantity() < cartOrnamentDto.getQuantity()) {
            throw new APIException("Please, make an order of the " + ornament.getName()
                    + " less than or equal to the quantity " + ornament.getQuantity() + ".");
        }

        CartOrnament cartOrnament = new CartOrnament();
        cartOrnament.setOrnament(ornament);
        cartOrnament.setOrnamentPrice(ornament.getPrice());
        cartOrnament.setQuantity(cartOrnamentDto.getQuantity());
        cartOrnament.setCart(cart);

        cart.getCartOrnaments().add(cartOrnament);
        cart.setTotalPrice(cart.getTotalPrice() + (cartOrnament.getOrnamentPrice() * cartOrnament.getQuantity()));
        ornament.setQuantity(ornament.getQuantity() - cartOrnament.getQuantity());

        return cartRepository.save(cart);

    }

    @Override
    public String removeFromCart(Integer cartId, Integer ornamentId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", String.valueOf(cartId)));

        CartOrnament cartOrnament = cartOrnamentRepository.findCartOrnamentByOrnamentIdAndCartId(cartId, ornamentId);

        if (cartOrnament == null) {
            throw new ResourceNotFoundException("Product", "productId", String.valueOf(ornamentId));
        }

        cart.setTotalPrice(cart.getTotalPrice() - (cartOrnament.getOrnamentPrice() * cartOrnament.getQuantity()));

        Ornament ornament = cartOrnament.getOrnament();
        ornament.setQuantity(ornament.getQuantity() + cartOrnament.getQuantity());

        cartOrnamentRepository.deleteCartOrnamentByOrnamentIdAndCartId(ornamentId, cartId);

        return "Ornament " + cartOrnament.getOrnament().getName() + " removed from the cart!!!";


    }

    @Override
    public List<Cart> getAllCarts() {
        return new ArrayList<>(cartRepository.findAll());
    }

    @Override
    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", String.valueOf(cartId)));
    }

    @Override
    public Cart updateCartOrnament(Integer cartId, Integer ornamentId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", String.valueOf(cartId)));
        Ornament ornament = ornamentRepository.findById(ornamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Ornament", "ornamentId", String.valueOf(ornamentId)));

        if (ornament.getQuantity() == 0) {
            throw new APIException(ornament.getName() + " is not available!");
        }
        if (ornament.getQuantity() < quantity) {
            throw new APIException("Please, make an order of the " + ornament.getName()
                    + " less than or equal to the quantity " + ornament.getQuantity() + ".");
        }

        CartOrnament cartOrnament = cartOrnamentRepository.findCartOrnamentByOrnamentIdAndCartId(cartId, ornamentId);

        if (cartOrnament == null) {
            throw new APIException("Ornament " + ornament.getName() + " not available in the cart!");
        }

        double cartPrice = cart.getTotalPrice() - (cartOrnament.getOrnamentPrice() * cartOrnament.getQuantity());

        ornament.setQuantity(ornament.getQuantity() + cartOrnament.getQuantity() - quantity);

        cartOrnament.setOrnamentPrice(ornament.getPrice());
        cartOrnament.setQuantity(quantity);

        cart.setTotalPrice(cartPrice + (cartOrnament.getOrnamentPrice() * quantity));

        cartOrnament = cartOrnamentRepository.save(cartOrnament);

        cart.getCartOrnaments().add(cartOrnament);


        return cartRepository.save(cart);
    }


}
