package com.example.service;

import com.example.model.SiteUser;
import com.example.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    private final SiteUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {

        // TODO:

        return null;
    }

    public SiteUser getUser(String username) {

        // TODO:

        return null;
    }
}
