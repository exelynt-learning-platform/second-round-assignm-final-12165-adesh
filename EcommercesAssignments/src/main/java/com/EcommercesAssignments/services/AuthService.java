package com.EcommercesAssignments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EcommercesAssignments.dto.Authdto;
import com.EcommercesAssignments.dto.Logindto;
import com.EcommercesAssignments.entity.User;
import com.EcommercesAssignments.reposi.UserRepo;
import com.EcommercesAssignments.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);

    }

    public Authdto login(Logindto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            String token = jwtUtil.generateToken(user.getEmail());
            return new Authdto(token);

        } else {
            throw new RuntimeException("Invalid password");
        }

    }
}
