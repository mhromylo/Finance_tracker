package com.example.financeTracker.demo.controller;

import com.example.financeTracker.demo.model.User;
import com.example.financeTracker.demo.repository.UserRepository;
import com.example.financeTracker.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        if (userService.getUserByUsername(username) != null)
        {
            return ResponseEntity.badRequest().body(null);
        }
        System.out.println("Registering " + username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {

        System.out.println("TRYING TO LOGIN");
        User user = userService.getUserByUsername(username);
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), userService.getUserByUsername(user.getUsername()).getPassword());
        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(userService.getUserByUsername(user.getUsername()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

}