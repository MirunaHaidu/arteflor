package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.user.UserRepository;
import com.demo.arteflor.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("test_qualifier_userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User addUser(UserDto userDto) {
        return userRepository.save(UserConvertor.convertDtoToEntity(userDto));
    }

    @Override
    public List<UserDto> getAllUsers() {
       return userRepository.findAll().stream()
               .map(UserConvertor::convertEntityToDto)
               .collect(Collectors.toList());
    }
}
