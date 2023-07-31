package com.demo.arteflor.controller.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.exception.UserNotFoundException;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.payloads.LoginCredentials;
import com.demo.arteflor.security.JWTUtil;
import com.demo.arteflor.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        String encodedPass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPass);

        User user = userService.addUser(userDto);

        String token = jwtUtil.generateToken(user.getEmail());

        return new ResponseEntity<>(Collections.singletonMap("jwt-token", token),
                HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@Valid @RequestBody LoginCredentials credentials) {
        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);
        String token = jwtUtil.generateToken(credentials.getEmail());

        Integer userId = userService.getUserIdByEmail(credentials.getEmail());
        if (userId != null && userId > 0) {
            // Adăugați ID-ul utilizatorului la răspunsul pe care îl returnați
            Map<String, Object> response = new HashMap<>();
            response.put("jwt-token", token);
            response.put("id", userId);
            return response;
        } else {
            return Collections.singletonMap("jwt-token", token);
        }
    }

}




