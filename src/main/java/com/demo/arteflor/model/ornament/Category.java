package com.demo.arteflor.model.ornament;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(unique = true)
//    private Integer categoryCode;
    @Column(unique = true)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Ornament> ornaments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Type> types;




}
