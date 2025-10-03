package com.blogger.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.APIErrorEntity;

import com.blogger.entity.UserEntity;
import com.blogger.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            UserEntity user = userService.getUserByUsername(username);
            if (user != null) {
                return ResponseEntity.ok(new APIResponseEntity<>(200, "User fetched successfully!", user));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorEntity(404, "User not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            UserEntity createdUser = userService.addUser(user.getUsername(), user.getEmail(), user.getPassword(),
                    user.getRole());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponseEntity<>(201, "User Created Successfully!", createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity userDetails) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String oldUsername = authentication.getName();
            UserEntity updatedUser = userService.updateUserByUsername(
                    oldUsername,
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getPassword());
            if (updatedUser != null) {
                return ResponseEntity.ok(new APIResponseEntity<>(200, "User updated successfully!", updatedUser));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorEntity(404, "User not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            UserEntity deletedUser = userService.deleteUserByUsername(username);
            if (deletedUser != null) {
                return ResponseEntity.ok(new APIResponseEntity<>(200, "User deleted successfully!", deletedUser));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorEntity(404, "User not found!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }
}
