package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.AddressConvertor;
import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.user.AddressRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.user.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("test_qualifier_addressServiceImpl")
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Address addAddress(AddressDto addressDto) {
        Address address = AddressConvertor.convertDtoToEntity(addressDto);
        String country = addressDto.getCountry();
        String county = addressDto.getCounty();
        String city = addressDto.getCity();
        String zipCode = addressDto.getZipCode();
        String name = addressDto.getName();
        Integer number = addressDto.getNumber();
        Address addressFromDB = addressRepository.findByNameAndNumberAndCountryAndCountyAndCityAndZipCode(name, number,
                country, county, city, zipCode);

        if (addressFromDB != null) {
            throw new APIException("Address already exists with addressId: " + addressFromDB.getId());
        }

        return addressRepository.save(address);
    }

}
