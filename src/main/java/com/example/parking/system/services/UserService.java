package com.example.parking.system.services;

import com.example.parking.system.models.User;
import com.example.parking.system.payload.request.LoginRequest;
import com.example.parking.system.payload.request.SignupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;


public interface UserService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

    ResponseEntity<?> logourUser();
}
