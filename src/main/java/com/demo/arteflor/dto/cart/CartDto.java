package com.demo.arteflor.dto.cart;

import com.demo.arteflor.dto.ornament.OrnamentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private Integer id;
    private Double totalPrice = 0.0;

}
