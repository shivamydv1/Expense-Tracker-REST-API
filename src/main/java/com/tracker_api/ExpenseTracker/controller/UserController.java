package com.tracker_api.ExpenseTracker.controller;

import com.tracker_api.ExpenseTracker.DTO.UserDto;
import com.tracker_api.ExpenseTracker.model.User;
import com.tracker_api.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<?>  addUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
