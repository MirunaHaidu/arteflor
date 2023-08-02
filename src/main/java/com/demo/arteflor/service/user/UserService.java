package com.demo.arteflor.service.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;

import java.util.List;

public interface UserService {

    User addUser(UserDto userDto);
    String addAdminRole(Integer userId);
    List<UserDto> getAllUsers();
    User updateUser(Integer userId, UserDto userDto);
    User getUserById(Integer userId);
    Integer getUserIdByEmail(String email);

    String deleteUser(Integer userId);

}
