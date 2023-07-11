package com.demo.arteflor.dto.cart;

import com.demo.arteflor.dto.ornament.OrnamentDto;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.ornament.Ornament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartOrnamentDto {

    private Integer id;
    private Integer ornamentId;
    private Integer cartId;
    private Integer quantity;
}
