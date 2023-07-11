package com.demo.arteflor.model.ornament;

import com.demo.arteflor.model.cart.CartOrnament;
import com.demo.arteflor.model.order.OrderOrnament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity(name = "ornaments")
public class Ornament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer code;
    @Column
    private String name;
    @Column(name = "image")
    private String imageURL;
    @Column
    private Double price;
    @Column
    private Integer quantity;
    @Column
    private Dimension dimension;  // 2D sau 3D
    @Column
    private String color;
    @Column
    private Boolean withWire;
    @Column
    private Double size;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;  //floare sau figurina

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "types_id")
    private Type type;

    @OneToMany(mappedBy = "ornament", cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<CartOrnament> cartOrnaments = new ArrayList<>();

    @OneToMany(mappedBy = "ornament", cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<OrderOrnament> orderOrnaments = new ArrayList<>();





}
