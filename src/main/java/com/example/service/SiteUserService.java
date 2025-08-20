package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.SiteUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class SiteUserService {

    @Autowired
    private SiteUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

}
