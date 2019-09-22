package com.fluex404.springcaching.controller;

import com.fluex404.springcaching.model.User;
import com.fluex404.springcaching.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.insert(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
