package com.github.vickyrai01.salesmanagement.repository;

import com.github.vickyrai01.salesmanagement.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
