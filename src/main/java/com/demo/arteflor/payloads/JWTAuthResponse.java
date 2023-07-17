package com.demo.arteflor.payloads;

import com.demo.arteflor.model.user.User;
import lombok.Data;

@Data
public class JWTAuthResponse {
    private String token;
    private User user;
}
