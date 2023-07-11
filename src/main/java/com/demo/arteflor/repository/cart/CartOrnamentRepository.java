package com.demo.arteflor.repository.cart;

import com.demo.arteflor.model.cart.CartOrnament;
import com.demo.arteflor.model.ornament.Ornament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartOrnamentRepository extends JpaRepository<CartOrnament, Integer> {
    Ornament findByOrnamentId(Integer ornamentId);

    @Query("SELECT ci FROM cart_ornaments ci WHERE ci.cart.id = ?1 AND ci.ornament.id = ?2")
    CartOrnament findCartOrnamentByOrnamentIdAndCartId(Integer cartId, Integer ornamentId);

    @Modifying
    @Query("DELETE FROM cart_ornaments ci WHERE ci.cart.id = ?1 AND ci.ornament.id = ?2")
    void deleteCartOrnamentByOrnamentIdAndCartId(Integer ornamentId, Integer cartId);


}
