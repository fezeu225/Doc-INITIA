package com.example.intia_assurance_test.controller;

import com.example.intia_assurance_test.exception.ResourceNotFoundException;
import com.example.intia_assurance_test.model.User;
import com.example.intia_assurance_test.repository.UserRepository;
import com.example.intia_assurance_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long UserId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this code :: " + UserId));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long UserId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(userDetails.getPassword());

        user.setNom(userDetails.getNom());
        user.setEmail(userDetails.getEmail());
        user.setPassword(encodePassword);
        user.setRole(userDetails.getRole());
        user.setEnabled(userDetails.isEnabled());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long UserId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
