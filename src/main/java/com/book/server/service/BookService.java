package com.book.server.service;

import org.springframework.web.bind.annotation.PathVariable;

import com.book.server.payload.BookDto;


//@FeignClient(name = "BOOK-SERVICE")
public interface BookService {
//	@GetMapping("/api/books/{id}")
	public BookDto getBookById(@PathVariable("id") Integer id);
}
