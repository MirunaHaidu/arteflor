package com.demo.arteflor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
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
    @JoinColumn(name = "category_id")
    private Category category;  //floare sau figurina

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

}
