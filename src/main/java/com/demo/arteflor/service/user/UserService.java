package com.demo.arteflor.service.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;

import java.util.List;

public interface UserService {

    User addUser(UserDto userDto);
    List<UserDto> getAllUsers();
}
