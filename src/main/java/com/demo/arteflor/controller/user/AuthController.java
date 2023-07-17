package com.demo.arteflor.controller.user;

import com.demo.arteflor.dto.user.UserDto;
import com.demo.arteflor.exception.UserNotFoundException;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.payloads.LoginCredentials;
import com.demo.arteflor.security.JWTUtil;
import com.demo.arteflor.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserDto userDto)throws UserNotFoundException{
        String encodedPass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPass);

        User user = userService.addUser(userDto);

        String token = jwtUtil.generateToken(user.getEmail());

        return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("jwt-token", token),
                HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@Valid @RequestBody LoginCredentials credentials){
        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);
        String token = jwtUtil.generateToken(credentials.getEmail());

        return Collections.singletonMap("jwt-token", token);
    }


    private List<String> tokenBlacklist = new ArrayList<>();
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logoutHandler(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7); // Extragere token din header

            // Adăugăm token-ul în lista neagră pentru a-l invalida la logout
            tokenBlacklist.add(jwtToken);

            // În plus, puteți efectua și alte acțiuni specifice pentru logout, de exemplu, ștergerea unui token de refresh

            // Răspuns cu codul HTTP 200 OK pentru a indica faptul că logout-ul a fost efectuat cu succes
            return ResponseEntity.ok().build();
        }

        // Răspuns cu codul HTTP 400 Bad Request sau alt cod corespunzător în cazul în care nu se poate efectua logout
        return ResponseEntity.badRequest().build();
    }
}







