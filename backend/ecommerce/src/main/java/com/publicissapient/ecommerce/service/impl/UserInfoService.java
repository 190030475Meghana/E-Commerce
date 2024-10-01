package com.publicissapient.ecommerce.service.impl;

import com.publicissapient.ecommerce.entities.Address;
import com.publicissapient.ecommerce.entities.UserInfo;
import com.publicissapient.ecommerce.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByEmail(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo, Address address) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo.setAddress(address);
        address.setUserInfo(userInfo);

        repository.save(userInfo);
        return "User Added Successfully";
    }



}
