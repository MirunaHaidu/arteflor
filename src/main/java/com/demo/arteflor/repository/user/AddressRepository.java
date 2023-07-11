package com.demo.arteflor.repository.user;

import com.demo.arteflor.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByNameAndNumberAndCountryAndCountyAndCityAndZipCode(String name, Integer number, String country,
                                                                           String county, String city, String zipCode);
}
