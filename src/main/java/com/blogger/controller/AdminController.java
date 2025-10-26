package com.blogger.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogger.entity.APIErrorEntity;
import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.ExternalUserEntity;
import com.blogger.entity.UserEntity;
import com.blogger.service.AdminService;
import com.blogger.service.ExternalUserService;

import lombok.extern.slf4j.Slf4j;

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

}
