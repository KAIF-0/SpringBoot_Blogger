package com.blogger.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogger.entity.UserEntity;
import com.blogger.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity addUser(String username, String email, String password, UserEntity.Role role) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, email, encodedPassword, role);
        userRepository.save(user);
        return user;
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserEntity deleteUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            userRepository.deleteById(user.getId());
        }
        return user;
    }

    public UserEntity updateUserByUsername(String oldUsername, String username, String email, String password) {
        UserEntity user = userRepository.findByUsername(oldUsername).orElse(null);
        if (user != null) {
            if (!oldUsername.equals(username) && userRepository.findByUsername(username).isPresent()) {
                throw new IllegalArgumentException("Username already exists");
            }
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
        return user;
    }
}
