package com.book.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }


 // Getters and setters...
}