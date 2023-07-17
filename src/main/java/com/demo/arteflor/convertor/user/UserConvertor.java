package com.demo.arteflor.convertor.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.user.UserRepository;

public class UserConvertor {

    private final UserRepository userRepository;

    public UserConvertor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static User convertDtoToEntity(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
