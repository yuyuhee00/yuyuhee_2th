package com.example.yuyuhee_2th.service;

import com.example.yuyuhee_2th.model.SiteUser;
import com.example.yuyuhee_2th.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;

    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {

        // TODO:

        return null;
    }

    public SiteUser getUser(String username) {

        // TODO:
        this.siteUserRepository.findByUsername(username);
    }
}
