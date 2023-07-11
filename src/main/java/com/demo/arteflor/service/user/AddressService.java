package com.demo.arteflor.service.user;

import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.model.user.Address;

public interface AddressService {
    Address addAddress(AddressDto addressDto);

}
