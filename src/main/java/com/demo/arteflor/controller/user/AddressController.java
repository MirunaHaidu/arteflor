package com.demo.arteflor.controller.user;

import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.service.user.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/address")
@ControllerAdvice
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Address> addAddress(@RequestBody @Valid AddressDto addressDto){
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }
}
