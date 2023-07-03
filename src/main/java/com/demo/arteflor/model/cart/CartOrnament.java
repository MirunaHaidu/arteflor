package com.demo.arteflor.model.cart;

import com.demo.arteflor.model.ornament.Ornament;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "cart_ornaments")
public class CartOrnament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;
    @Column
    private Double ornamentPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ornament_id")
    private Ornament ornament;


}
