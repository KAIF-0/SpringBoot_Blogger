package com.blogger.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserEntity> users = userService.getAllUsers();
            return ResponseEntity.ok(new APIResponseEntity<>(200, "Users fetched successfully!", users));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserEntity user = userService.getUserById(id);
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
            UserEntity createdUser = userService.addUser(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponseEntity<>(201, "User Created Successfully!", createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorEntity(500, e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
        try {
            UserEntity updatedUser = userService.updateUser(
                    id,
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            UserEntity deletedUser = userService.deleteUserById(id);
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
