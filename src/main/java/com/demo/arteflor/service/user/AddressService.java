package com.demo.arteflor.service.user;

import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.model.user.Address;

import java.util.List;

public interface AddressService {
    Address addAddress(AddressDto addressDto);
    String addAddressToUser(Integer addressId, Integer userId);
    List<AddressDto> getAddresses();
    Address getById(Integer addressId);
    Address updateAddress(Integer addressId, AddressDto addressDto);
    String deleteAddress(Integer addressId);
}
