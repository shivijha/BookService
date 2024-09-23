package com.book.server.payload;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;

}
