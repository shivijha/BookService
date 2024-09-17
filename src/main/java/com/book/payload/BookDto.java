package com.book.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
	private Long id;
    private String title;
    private String author;
    private String genre;
    private Integer price; 
}
