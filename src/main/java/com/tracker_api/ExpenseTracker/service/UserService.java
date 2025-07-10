package com.tracker_api.ExpenseTracker.service;

import com.tracker_api.ExpenseTracker.DTO.UserDto;
import com.tracker_api.ExpenseTracker.model.User;
import com.tracker_api.ExpenseTracker.repository.UserRepository;
//import com.tracker_api.ExpenseTracker.security.JwtUtills;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User(dto.getName(), dto.getEmail(), dto.getPassword(), dto.getMonthlyBudget());
        userRepository.save(user);


        return "User registered successfully";

    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
