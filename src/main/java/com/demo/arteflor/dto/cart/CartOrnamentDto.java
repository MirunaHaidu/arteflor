package com.demo.arteflor.dto.cart;

import com.demo.arteflor.dto.ornament.OrnamentDto;
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
    private CartDto cart;
    private OrnamentDto ornament;
    private Integer quantity;
    private Double ornamentPrice;
}
