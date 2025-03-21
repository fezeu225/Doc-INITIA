package com.example.intia_assurance_test.repository;

import com.example.intia_assurance_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String username);
}
