package com.demo.arteflor.model.ornament;

import com.demo.arteflor.model.ornament.Category;
import com.demo.arteflor.model.ornament.Ornament;
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
@Entity(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
