package com.demo.arteflor.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {
    private Integer id;
    private String addressNameAndNumber;
    private String city;
    private String county;
    private String country;
    private String zipCode;

}
