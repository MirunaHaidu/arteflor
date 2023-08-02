package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.exception.APIException;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.cart.Cart;
import com.demo.arteflor.model.cart.CartOrnament;
import com.demo.arteflor.model.user.Role;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.cart.CartRepository;
import com.demo.arteflor.repository.user.AddressRepository;
import com.demo.arteflor.repository.user.RoleRepository;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.security.JWTUtil;
import com.demo.arteflor.service.cart.CartService;
import com.demo.arteflor.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final JWTUtil jwtUtil;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository, RoleRepository roleRepository, JWTUtil jwtUtil, CartService cartService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.cartService = cartService;
        this.passwordEncoder = passwordEncoder;
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
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new APIException("User already exists with email: " + userDto.getEmail());
        }
    }

    @Override
    public String addAdminRole(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        Role adminRole = roleRepository.findByRoleName("ADMIN");
        user.getRoles().add(adminRole);
        userRepository.save(user);

        return "User with id " + userId + " now has ADMIN role!";
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
    }

    @Override
    public Integer getUserIdByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }


    @Override
    public String deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));

        List<CartOrnament> cartOrnaments = user.getCart().getCartOrnaments();
        Integer cartId = user.getCart().getCartId();

        cartOrnaments.forEach(ornament -> {
            Integer ornamentId = ornament.getOrnament().getId();
            cartService.removeFromCart(cartId, ornamentId);
        });

        userRepository.delete(user);
        return "User with id " + userId + " deleted successfully!";
    }


}

