package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.AddressConvertor;
import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.user.AddressRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.user.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        String street = addressDto.getStreet();
        Integer number = addressDto.getNumber();
        Address addressFromDB = addressRepository.findByStreetAndNumberAndCountryAndCountyAndCityAndZipCode(street, number,
                country, county, city, zipCode);

        if (addressFromDB != null) {
            throw new APIException("Address already exists with addressId: " + addressFromDB.getId());
        }

        return addressRepository.save(address);
    }

    @Override
    public String addAddressToUser(Integer addressId, Integer userId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", String.valueOf(addressId)));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        user.getAddresses().add(address);

        return "Address added successfully!";
    }

    @Override
    public List<AddressDto> getAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Address getById(Integer addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", String.valueOf(addressId)));
    }

    @Override
    public Address updateAddress(Integer addressId, AddressDto addressDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", String.valueOf(addressId)));

       if(addressDto.getStreet()!=null){
           address.setStreet(addressDto.getStreet());
       }
       if(addressDto.getNumber()!=null){
           address.setNumber(addressDto.getNumber());
       }
       if (addressDto.getCountry()!=null){
           address.setCountry(addressDto.getCountry());
       }
       if(addressDto.getCounty()!=null){
           address.setCounty(addressDto.getCounty());
       }
       if(addressDto.getCity()!=null){
           address.setCity(addressDto.getCity());
       }
       if(addressDto.getZipCode()!=null){
           address.setZipCode(addressDto.getZipCode());
       }

       return addressRepository.save(address);
    }

    @Override
    public String deleteAddress(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", String.valueOf(addressId)));

        List<User> users = address.getUsers();
        users.forEach(user -> {
            user.getAddresses().remove(address);
            userRepository.save(user);
        });

        addressRepository.deleteById(addressId);
        return "Address deleted successfully!";
    }


}
