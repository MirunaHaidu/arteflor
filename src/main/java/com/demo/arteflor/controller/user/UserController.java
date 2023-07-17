package com.demo.arteflor.controller.user;

import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@ControllerAdvice
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/admin/user/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
