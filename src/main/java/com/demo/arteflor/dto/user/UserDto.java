package com.demo.arteflor.dto.user;

import com.demo.arteflor.dto.cart.CartDto;
import com.demo.arteflor.model.user.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private AddressDto address;
    private CartDto cart;

}
