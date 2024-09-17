package com.book.controller;

import com.book.payload.BookDto;
import com.book.serviceImpl.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    // Get all books with pagination
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
    		@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<BookDto> books = bookService.getAllBooks(page, size);
        return ResponseEntity.ok(books);
    }

    // Save a new book
    @PostMapping
    public ResponseEntity<List<BookDto>> createBook(@RequestBody List<BookDto> bookDTO) {
    	List<BookDto> savedBook = bookService.saveBooks(bookDTO);
        return ResponseEntity.ok(savedBook);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
    	BookDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // Update a book by ID
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @RequestBody BookDto bookDTO) {
    	BookDto updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
