package com.example.yuyuhee_2th.repository;

import com.example.yuyuhee_2th.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer> {

    // TODO:
    Optional<SiteUser> findByUsername(String username);
}
