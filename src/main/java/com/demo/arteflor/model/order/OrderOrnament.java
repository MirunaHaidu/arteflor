package com.demo.arteflor.model.order;

import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.model.ornament.Ornament;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "order_ornaments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderOrnament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ornaments_id")
    private Ornament ornament;
}
