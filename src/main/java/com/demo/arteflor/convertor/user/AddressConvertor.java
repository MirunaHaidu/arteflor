package com.demo.arteflor.convertor.user;

import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.repository.user.AddressRepository;

public class AddressConvertor {
    private final AddressRepository addressRepository;

    public AddressConvertor(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public static Address convertDtoToEntity(AddressDto addressDto){
        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setCounty(addressDto.getCounty());
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());

        return address;
    }

    public static AddressDto convertEntityToDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(address.getCountry());
        addressDto.setCounty(address.getCounty());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());

        return addressDto;
    }
}
