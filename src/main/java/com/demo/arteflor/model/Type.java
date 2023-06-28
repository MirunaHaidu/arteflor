package com.demo.arteflor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    @Column(unique = true)
//    private Integer typeCode;
    @Column(unique = true)
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;  // ce fel de floare sau figurina

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Ornament> ornaments;
}
