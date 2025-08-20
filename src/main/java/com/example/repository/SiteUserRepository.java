package com.example.repository;

import com.example.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer> {

    // TODO:

}
