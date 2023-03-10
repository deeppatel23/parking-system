package com.example.parking.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parking.system.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  User findByEmailIgnoreCase(String emailId);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
