package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.AddressConvertor;
import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.AddressDto;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.model.user.Role;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.user.AddressRepository;
import com.demo.arteflor.repository.user.RoleRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public User addUser(UserDto userDto) {
        try {
            User user = UserConvertor.convertDtoToEntity(userDto);
            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);

            Role role = roleRepository.findByRoleName("ADMIN");
            user.getRoles().add(role);

            if (userDto.getAddress() != null) {
                user.setAddresses(List.of(userDto.getAddress()));
            } else {
                user.setAddresses(Collections.emptyList());
            }


            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            throw new APIException("User already exists with email: "+userDto.getEmail());
        }
    }

    @Override
    public void addAdminRoleToUser(User user) {
        Role adminRole = roleRepository.findByRoleName("ADMIN");
        user.getRoles().add(adminRole);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       return userRepository.findAll().stream()
               .map(UserConvertor::convertEntityToDto)
               .collect(Collectors.toList());
    }


    @Override
    public String deleteUser(Integer userId) {
        return null;
    }


}
