package com.demo.arteflor.repository.cart;

import com.demo.arteflor.model.cart.Cart;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getById(Integer id);
    @Query("SELECT c FROM carts c WHERE c.user.email = ?1 AND c.id = ?2")
    Cart findCartByEmailAndId(String email, Integer id);

}
