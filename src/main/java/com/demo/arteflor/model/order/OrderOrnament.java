package com.demo.arteflor.model.order;

import com.demo.arteflor.model.order.Order;
import com.demo.arteflor.model.ornament.Ornament;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class OrderOrnament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @Column
    private LocalDate createdDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ornaments_id")
    private Ornament ornament;
}
