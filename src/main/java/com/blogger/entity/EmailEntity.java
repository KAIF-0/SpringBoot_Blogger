package com.blogger.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailEntity {
    private String to;
    private String subject; 
    private String body;
}
