package com.blogger.entity;
import lombok.*;


@Data
@NoArgsConstructor
public class APIResponseEntity<T> {
    private int statusCode;
    private String message;
    private T data;

    public APIResponseEntity(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
