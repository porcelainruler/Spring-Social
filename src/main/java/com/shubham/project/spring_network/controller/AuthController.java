package com.shubham.project.spring_network.controller;

import com.shubham.project.spring_network.dto.request.AuthRequest;
import com.shubham.project.spring_network.dto.response.JwtResponse;
import com.shubham.project.spring_network.security.CustomUserDetailsService;
import com.shubham.project.spring_network.service.JwtService;
import com.shubham.project.spring_network.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${apiPrefixV1}/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping(path = "/login")
    public ResponseEntity<JwtResponse> generateJwtToken (@RequestBody AuthRequest authRequest) {
        Authentication authentication =  this.doAuthenticate(authRequest.getUsername(), authRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = this.jwtService.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        JwtResponse response = new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private Authentication doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            return authManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}