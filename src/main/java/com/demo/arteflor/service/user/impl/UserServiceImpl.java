package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.user.Address;
import com.demo.arteflor.model.user.Role;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.user.AddressRepository;
import com.demo.arteflor.repository.user.RoleRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.user.UserService;
import jakarta.persistence.SecondaryTable;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

            Role role = roleRepository.findByRoleName("USER");
            user.getRoles().add(role);

//        Address address = addressRepository.findById(userDto.getAddressId())
//                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", String.valueOf(userDto.getAddressId())));

            String country = userDto.getAddress().getCountry();
            String county = userDto.getAddress().getCounty();
            String city = userDto.getAddress().getCity();
            String zipCode = userDto.getAddress().getZipCode();
            String name = userDto.getAddress().getName();
            Integer number = userDto.getAddress().getNumber();

            Address address = addressRepository.findByNameAndNumberAndCountryAndCountyAndCityAndZipCode(name, number,
                    country, county, city, zipCode);

            if (address == null) {
                address = new Address(country, county, city, zipCode, name, number);

                address = addressRepository.save(address);
            }

            user.getAddresses().add(address);

            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
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

//    @Override
//    public UserDto getUserByEmail(String email) {
//        User user = userRepository.findByEmail(email);
//        if(user == null){
//            throw new RuntimeException("User with email "+ email+" not found");
//        }
//        return UserConvertor.convertEntityToDto(user);
//    }

    @Override
    public String deleteUser(Integer userId) {
        return null;
    }


}
