package com.book.controller;

import com.book.entity.Book;
import com.book.payload.BookDto;
import com.book.serviceImpl.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
        System.out.println("Hitting"+ books);
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
    
    @GetMapping("/byName")
    public ResponseEntity<List<Book>> getBooksByName(@RequestParam(name = "name", required = true)String name) {
    	List<Book> bookByName = bookService.getBooksByName(name);
        return ResponseEntity.ok(bookByName);
    }

    @GetMapping("/byGenre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam(name = "genre", required = true)String genre) {
    	List<Book> bookByGenre = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(bookByGenre);
    }


    @GetMapping("/byPrice")
    public ResponseEntity<List<Book>> getBooksByPriceLessThan(@RequestParam(name = "price", required = true)Integer price) {
    	List<Book> bookByPrice = bookService.getBooksByPriceLessThan(price);
        return ResponseEntity.ok(bookByPrice);
    }
    
    @GetMapping("/byAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam(name = "author", required = true)String author) {
    	List<Book> bookByAuthor = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(bookByAuthor);
    }
    
    
    @GetMapping("/byAuthorandGenre")
    public ResponseEntity<List<Book>> getBooksByAuthorAndGenre(
    		@RequestParam(name = "author", required = true)String author,
    		@RequestParam(name = "genre", required = true)String genre) {
    	List<Book> bookByAuthorandGenre = bookService.getBooksByAuthorAndGenre(author,genre);
        return ResponseEntity.ok(bookByAuthorandGenre);
    }
}
