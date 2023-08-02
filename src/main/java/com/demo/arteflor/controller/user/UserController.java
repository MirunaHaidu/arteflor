package com.demo.arteflor.controller.user;

import com.demo.arteflor.convertor.user.UserConvertor;
import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.payloads.UserResponse;
import com.demo.arteflor.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/admin/user/addAdminRole")
    public ResponseEntity<String> addAdminRole(@RequestParam Integer userId){
        String status = userService.addAdminRole(userId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @GetMapping("/admin/user/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/public/user/getById")
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestParam Integer userId){
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PutMapping("/public/user/update")
    public ResponseEntity<User> updateUser(@RequestParam Integer userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userId, userDto);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("/admin/user/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Integer userId){
        String status = userService.deleteUser(userId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
