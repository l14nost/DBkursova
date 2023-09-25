package com.example.DBkursova.service;


public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {
    String getUserProfile();
}
