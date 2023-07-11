package com.demo.arteflor.service.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserByEmail(String email);
    String deleteUser(Integer userId);
}
