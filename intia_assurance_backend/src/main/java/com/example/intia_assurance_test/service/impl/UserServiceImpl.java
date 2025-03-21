package com.example.intia_assurance_test.service.impl;

import com.example.intia_assurance_test.config.util.PasswordUtil;
import com.example.intia_assurance_test.model.User;
import com.example.intia_assurance_test.repository.UserRepository;
import com.example.intia_assurance_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        String password = PasswordUtil.getPasswordHash(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User getUsersByLogin(String login) {
        return userRepository.findByEmailIgnoreCase(login);
    }

}
