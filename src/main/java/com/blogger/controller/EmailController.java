package com.blogger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.entity.APIResponseEntity;
import com.blogger.entity.EmailEntity;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blogger.service.EmailService;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody EmailEntity entity) {
        try {
            emailService.sendEmail(entity.getTo(), entity.getSubject(), entity.getBody());
            log.info("Email sent successfully to {} with subject '{}'", entity.getTo(), entity.getSubject());
            return ResponseEntity
                    .ok(new APIResponseEntity<>(HttpStatus.OK.value(), "Email sent successfully!", entity));
        } catch (Exception e) {
            log.error("Error sending email to {}: {}", entity != null ? entity.getTo() : "unknown", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Error sending email: " + e.getMessage(), null));
        }

    }

}
