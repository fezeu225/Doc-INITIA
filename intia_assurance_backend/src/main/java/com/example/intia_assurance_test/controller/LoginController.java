package com.example.intia_assurance_test.controller;

import com.example.intia_assurance_test.config.UnauthorizedException;
import com.example.intia_assurance_test.config.domain.Response;
import com.example.intia_assurance_test.config.domain.UserDTO;
import com.example.intia_assurance_test.config.security.JwtTokenUtil;
import com.example.intia_assurance_test.config.security.JwtUser;
import com.example.intia_assurance_test.model.User;
import com.example.intia_assurance_test.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            final JwtUser userDetails = (JwtUser) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(userDetails);
            response.setHeader("Token", token);
            return new ResponseEntity<>(new UserDTO(userDetails.getUser(), token), HttpStatus.OK);
        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Response> registration(@RequestBody User user) {
        User dbUser = userService.save(user);
        if (dbUser != null) {
            return new ResponseEntity<Response>(new Response("User is saved successfully"), HttpStatus.OK);
        }
        return null;
    }

}
