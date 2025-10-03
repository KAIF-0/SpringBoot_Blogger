package com.blogger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blogger.entity.APIErrorEntity;
import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.UserEntity;
import com.blogger.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
@Autowired
    private AdminService adminService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserEntity> users = adminService.getAllUsers();
            return ResponseEntity.ok(new APIResponseEntity<>(200, "Users fetched successfully!", users));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }
}
