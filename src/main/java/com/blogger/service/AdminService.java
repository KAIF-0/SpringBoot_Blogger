package com.blogger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogger.entity.UserEntity;
import com.blogger.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheService cacheService;


    public List<UserEntity> getAllUsers() {
        List<UserEntity> cachedUsers = cacheService.get("allUsers", UserEntity.class);
        if (cachedUsers != null) {
            return cachedUsers;
        }
        List<UserEntity> users = userRepository.findAll();
        // cacheService.set("allUsers", users, 300L); // Cache for 5 minutes
        return users;
    }

    public List<UserEntity> getUsersByRole(String role) {
        try {
            UserEntity.Role roleEnum = UserEntity.Role.valueOf(role.toUpperCase());
            return userRepository.findByRole(roleEnum);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role + ". Valid roles are: USER, ADMIN");
        }
    }

    public List<UserEntity> getUsersByRoleWithBlogs(String role ,int size) {
        try {
            UserEntity.Role roleEnum = UserEntity.Role.valueOf(role.toUpperCase());
            return userRepository.findUsersByRoleWithBlogs(roleEnum, size);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role + ". Valid roles are: USER, ADMIN");
        }
    }

}
