package com.example.yuyuhee_2th.service;

import com.example.yuyuhee_2th.model.SiteUser;
import com.example.yuyuhee_2th.repository.SiteUserRepository;
import com.example.yuyuhee_2th.utils.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;

    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email) {

        // TODO:
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setPassword(passwordEncoder.encode(password));
        siteUser.setEmail(email);
        this.siteUserRepository.save(siteUser);
        return null;
    }

    public SiteUser getUser(String username) {

        // TODO:
        Optional<SiteUser> siteUser = this.siteUserRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        }
        throw new DataNotFoundException("site user not found");
    }
}
