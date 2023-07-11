package com.demo.arteflor.dto.cart;

import com.demo.arteflor.dto.ornament.OrnamentDto;
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
    private Ornament ornamentId;
    private Integer quantity;
}
