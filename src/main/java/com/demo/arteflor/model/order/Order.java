package com.demo.arteflor.model.order;

import com.demo.arteflor.model.user.Payment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String orderStatus;
    @Column
    private Double totalPrice;
    @Column
    private LocalDateTime orderDateTime;


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderOrnament> orderOrnaments = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @JsonIgnore
    public List<OrderOrnament> getOrderOrnaments() {
        return orderOrnaments;
    }
}
