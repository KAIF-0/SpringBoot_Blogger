package com.blogger.entity;

import lombok.*;


@Data
@NoArgsConstructor
public class APIErrorEntity {
    private int statusCode;
    private String message;
    private String data;
    public APIErrorEntity(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }  
}
