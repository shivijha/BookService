package com.book.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.book.payload.BookDto;

//@FeignClient(name = "BOOK-SERVICE")
public interface BookService {
//	@GetMapping("/api/books/{id}")
	public BookDto getBookById(@PathVariable("id") Integer id);
}
