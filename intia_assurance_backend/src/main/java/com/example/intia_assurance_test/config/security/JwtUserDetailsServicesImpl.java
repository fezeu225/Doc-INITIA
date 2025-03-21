package com.example.intia_assurance_test.config.security;

import com.example.intia_assurance_test.model.User;
import com.example.intia_assurance_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsServicesImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("Authenticating user: {}", username);

        User user = userRepository.findByEmailIgnoreCase(username);
        if (user == null) {
//            log.error("User not found: {}", username);
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = (List<String>) user.getRole(); // Assurez-vous que `getRole()` renvoie une liste !

        if (roles != null) {
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
        }

//        log.info("User authenticated successfully: {}", username);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}