package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.user.AddressRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    @Transactional
    public User addUser(UserDto userDto) {
        User user = UserConvertor.convertDtoToEntity(userDto);
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        Address address = addressRepository.findById(userDto.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", String.valueOf(userDto.getAddressId())));

        user.getAddresses().add(address);

        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       return userRepository.findAll().stream()
               .map(UserConvertor::convertEntityToDto)
               .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User with email "+ email+" not found");
        }
        return UserConvertor.convertEntityToDto(user);
    }

    @Override
    public String deleteUser(Integer userId) {
        return null;
    }
}
