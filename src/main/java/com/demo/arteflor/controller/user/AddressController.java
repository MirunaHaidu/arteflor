package com.demo.arteflor.controller.user;

import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.service.user.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/public/address/addAddress")
    public ResponseEntity<Address> addAddress(@RequestBody @Valid AddressDto addressDto) {
        Address savedAddress = addressService.addAddress(addressDto);
        return new ResponseEntity<Address>(savedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/public/address/addAddressToUser")
    public ResponseEntity<String> addAddressToUser(@RequestParam Integer userId, @RequestParam Integer addressId){
        String status = addressService.addAddressToUser(addressId, userId);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @GetMapping("/admin/address/getAddresses")
    public ResponseEntity<List<AddressDto>> getAddresses(){
        List<AddressDto> addresses = addressService.getAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.FOUND);
    }

    @GetMapping("/admin/address/getAddressById")
    public ResponseEntity<Address> getAddressById(@RequestParam Integer addressId){
        Address address = addressService.getById(addressId);
        return new ResponseEntity<>(address, HttpStatus.FOUND);
    }

    @PutMapping("/public/address/update")
    public ResponseEntity<Address> updateAddress(@RequestParam Integer addressId, @RequestBody AddressDto addressDto) {
        Address address = addressService.updateAddress(addressId, addressDto);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/public/address/delete")
    public ResponseEntity<String> deleteAddress(@RequestParam Integer addressId){
        String status = addressService.deleteAddress(addressId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}
