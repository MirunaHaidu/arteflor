package com.demo.arteflor.controller.cart;

import com.demo.arteflor.dto.cart.CartOrnamentDto;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.service.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cart")
@ControllerAdvice
public class CartController {

    public final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addOrnamentToCart")
    public ResponseEntity<Cart> addOrnamentToCart(@RequestBody @Valid CartOrnamentDto cartOrnamentDto){
        return ResponseEntity.ok(cartService.addOrnamentToCart(cartOrnamentDto));
    }
}
