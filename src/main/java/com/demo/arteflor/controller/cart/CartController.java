package com.demo.arteflor.controller.cart;

import com.demo.arteflor.dto.cart.CartOrnamentDto;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.service.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
public class CartController {

    public final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/public/cart/addOrnamentToCart")
    public ResponseEntity<Cart> addOrnamentToCart(@RequestBody @Valid CartOrnamentDto cartOrnamentDto){
        Cart cart = cartService.addOrnamentToCart(cartOrnamentDto);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }
    @GetMapping("/admin/cart/getAllCarts")
    public ResponseEntity<List<Cart>> getAllCarts(){
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/admin/cart/getById")
    public ResponseEntity<Cart> getCartById(@RequestParam Integer cartId){
       Cart cart = cartService.getCartById(cartId);

        return new ResponseEntity<>(cart, HttpStatus.FOUND);
    }

    @PutMapping("/public/admin/update")
    public ResponseEntity<Cart> updateCartOrnament(@RequestParam Integer cartId,@RequestParam Integer ornamentId,@RequestParam Integer quantity) {
        Cart cart = cartService.updateCartOrnament(cartId, ornamentId, quantity);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
