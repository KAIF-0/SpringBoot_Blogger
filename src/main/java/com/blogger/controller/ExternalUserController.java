package com.blogger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.BlogEntity;
import com.blogger.entity.ExternalUserEntity;
import com.blogger.service.ExternalUserService;

@RestController
@RequestMapping("/externalUsers")
public class ExternalUserController {

    @Autowired
    private ExternalUserService externalUserService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ExternalUserController.class);

    @GetMapping("/getAllExternalUsers")
    public ResponseEntity<?> getAllUsersExternal() {
        try {
            ExternalUserEntity[] users = externalUserService.fetchExternalUserData();

            logger.info("Fetched all users successfully for external API. Total users: {}", users.length);
            return ResponseEntity.ok(new APIResponseEntity<>(200, "External Users fetched successfully!", users));
        } catch (Exception e) {
            logger.error("Error fetching users for external API: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching users: " + e.getMessage());
        }
    }

    @PostMapping("/createExternalUsers")
    public ResponseEntity<?> createExternalUsers(@RequestBody ExternalUserEntity externalUser) {
        try {
            ExternalUserEntity users = externalUserService.createExternalUser(externalUser);

            logger.info("Created a new external user. New users: {}", users);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponseEntity<>(201, "External Users created successfully!", users));
        } catch (Exception e) {
            logger.error("Error creating users using external API: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating users: " + e.getMessage());
        }
    }
}
