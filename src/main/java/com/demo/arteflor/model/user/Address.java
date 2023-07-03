package com.demo.arteflor.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank
    @Size(min = 5, message = "Street name must contain at least 5 characters")
    private String addressNameAndNumber;

    @Column
    @NotBlank
    @Size(min = 4, message = "City name must contain at least 4 characters")
    private String city;

    @Column
    @NotBlank
    @Size(min = 2, message = "County name must contain at least 2 characters")
    private String county;

    @NotBlank
    @Size(min = 2, message = "Country name must contain at least 2 characters")
    private String country;

    @NotBlank
    @Size(min = 6, message = "Zip code must contain at least 6 characters")
    private String zipCode;

    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String country, String county, String city, String zipCode, String addressNameAndNumber){
        this.country = country;
        this.county = county;
        this.city = city;
        this.zipCode = zipCode;
        this.addressNameAndNumber = addressNameAndNumber;
    }





}
