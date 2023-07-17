package com.demo.arteflor.service.user.impl;

import com.demo.arteflor.config.UserInfoConfig;
import com.demo.arteflor.exception.ResourceNotFoundException;
import com.demo.arteflor.model.user.User;
import com.demo.arteflor.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(UserInfoConfig::new).orElseThrow(()->new ResourceNotFoundException("User", "email", username));

    }
}
