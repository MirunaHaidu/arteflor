package com.demo.arteflor.dto;

import com.demo.arteflor.model.Category;
import com.demo.arteflor.model.Dimension;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrnamentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer code;
    private String name;
    private String imageURL;
    private Double price;
    private Dimension dimension;
    private String color;
    private Double size;
    private Boolean withWire;
    private String description;
    private String ingredients;

    private String typeTitle;
}
