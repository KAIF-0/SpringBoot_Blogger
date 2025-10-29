package com.blogger.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.entity.APIErrorEntity;
import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.UserEntity;
import com.blogger.service.AdminService;

@RestController
@RequestMapping("/admin")
// @Slf4j  //both can be used for injecting logger
public class AdminController {

    @Autowired
    private AdminService adminService;


    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);  //associated with only one class

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserEntity> users = adminService.getAllUsers();
            
            logger.info("Fetched all users successfully. Total users: {}", users.size());
            return ResponseEntity.ok(new APIResponseEntity<>(200, "Users fetched successfully!", users));
        } catch (Exception e) {
            logger.error("Error fetching users: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @GetMapping("/getUsersByRole/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        try {
            List<UserEntity> users = adminService.getUsersByRole(role);
            
            logger.info("Fetched users with role '{}' successfully. Total users: {}", role, users.size());
            return ResponseEntity.ok(new APIResponseEntity<>(200, "Users with role '" + role + "' fetched successfully!", users));
        } catch (Exception e) {
            logger.error("Error fetching users with role '{}': {}", role, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @GetMapping("/getUsersByRoleWithBlogs/{role}/{size}")
    public ResponseEntity<?> getUsersByRoleWithBlogs(@PathVariable String role, @PathVariable int size) {
        try {
            List<UserEntity> usersWithBlogs = adminService.getUsersByRoleWithBlogs(role, size);
            
            logger.info("Fetched users with role '{}' and blogs successfully. Total users: {}", role, usersWithBlogs.size());
            return ResponseEntity.ok(new APIResponseEntity<>(200, "Users with role '" + role + "' and blogs fetched successfully!", usersWithBlogs));
        } catch (Exception e) {
            logger.error("Error fetching users with role '{}' and blogs: {}", role, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

}
