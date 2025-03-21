package com.example.intia_assurance_test.service;

import com.example.intia_assurance_test.model.User;
import com.example.intia_assurance_test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);

    List<User> findAll();

    User getUsersByLogin(String name);
}