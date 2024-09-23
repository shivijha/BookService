package com.book.server.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;
    
}