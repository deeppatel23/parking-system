package com.example.parking.system.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.parking.system.models.ERole;
import com.example.parking.system.models.Role;
import com.example.parking.system.payload.request.LoginRequest;
import com.example.parking.system.payload.response.MessageResponse;
import com.example.parking.system.payload.response.UserInfoResponse;
import com.example.parking.system.repository.RoleRepository;
import com.example.parking.system.repository.UserRepository;
import com.example.parking.system.security.jwt.JwtUtils;
import com.example.parking.system.services.UserDetailsImpl;
import com.example.parking.system.services.UserService;
import com.example.parking.system.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.parking.system.models.User;
import com.example.parking.system.payload.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private UserService userService;
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    return userService.authenticateUser(loginRequest);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
      return userService.registerUser(signUpRequest);
    }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
      return userService.logourUser();
  }

  @GetMapping("/details/{username}")
  public ResponseEntity<?> getUserDetails(@PathVariable("username") String username) {
    return userService.getUserDetails(username);
  }
}
