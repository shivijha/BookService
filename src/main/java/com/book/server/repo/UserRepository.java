package com.book.server.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.server.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

