package com.demo.arteflor.repository.user;

import com.demo.arteflor.model.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByStreetAndNumberAndCountryAndCountyAndCityAndZipCode(String street, Integer number, String country,
                                                                           String county, String city, String zipCode);
}
