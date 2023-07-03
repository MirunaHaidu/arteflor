package com.demo.arteflor.model.order;

import com.demo.arteflor.model.user.Payment;
import com.demo.arteflor.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String orderStatus;
    @Column
    private Double totalPrice;
    @Column
    private LocalDate orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderOrnament> orderOrnaments = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "users_id")
//    private User user;
}
