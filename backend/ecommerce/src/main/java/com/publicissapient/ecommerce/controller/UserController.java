package com.publicissapient.ecommerce.controller;

import com.publicissapient.ecommerce.dto.UserRegistrationTO;
import com.publicissapient.ecommerce.entities.*;
import com.publicissapient.ecommerce.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/welcome")
    public static String welcome() {
        return "Welcome";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserRegistrationTO userRegistrationTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userRegistrationTO.getName());
        userInfo.setEmail(userRegistrationTO.getEmail());
        userInfo.setPassword(userRegistrationTO.getPassword());

        Address address = new Address();
        address.setStreet(userRegistrationTO.getStreet());
        address.setCity(userRegistrationTO.getCity());
        address.setZipCode(userRegistrationTO.getZipCode());

        return userInfoService.addUser(userInfo, address);

    }

    @PostMapping("/generateToken")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userInfoService.loadUserByUsername(authRequest.getUsername());
            Long userId = ((UserInfoDetails) userDetails).getUserId();
            String token = jwtService.generateToken(authRequest.getUsername());
            return new AuthResponse(token, authRequest.getUsername(), userId);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }




}
