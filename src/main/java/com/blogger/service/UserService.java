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

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity addUser(String username, String email, String password, UserEntity.Role role) {
        String encodedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(username, email, encodedPassword, role);
        userRepository.save(user);
        return user;
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity deleteUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
        }
        return user;
    }

    public UserEntity updateUser(Long id, String username, String email, String password) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password)); // Encode the password
            userRepository.save(user);
        }
        return user;
    }
}
