package com.example.intia_assurance_test.config.security;

import com.example.intia_assurance_test.model.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), user,
                maptoGrantedAuthorities(new ArrayList<String>(List.of("ROLE " + user.getRole()))), user.isEnabled());
    }

    private static List<GrantedAuthority> maptoGrantedAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
