package com.Spring.auth;




import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Spring.model.User;
import com.Spring.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(User user) {
//        if (((Object) userRepository.findByUsername(user.getUsername())).isPresent()) {
//            return "User already exists!";
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.print(user.getUsername());
        System.out.print(user.getPassword());
        return "User registered successfully!";
    }
}

