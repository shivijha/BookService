package com.book.serviceImpl;

import com.book.entity.Book;
import com.book.exception.BookNotFoundException;
import com.book.payload.BookDto;
import com.book.repo.BookRepository;
import com.book.repo.UserRepository;
//import com.book.service.BookService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Convert Book to BookDTO using ModelMapper
    private BookDto convertToDTO(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    // Convert BookDTO to Book entity using ModelMapper
    private Book convertToEntity(BookDto bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    // Get paginated books
    public List<BookDto> getAllBooks(int page, int size) {
        Page<Book> paginatedBooks = bookRepository.findAll(PageRequest.of(page, size));
        return paginatedBooks.stream()
                             .map(this::convertToDTO)
                             .collect(Collectors.toList());
    }

    // Save a new book
    public List<BookDto> saveBooks(List<BookDto> bookDtos) {
        List<Book> books = bookDtos.stream()
                                   .map(this::convertToEntity)
                                   .collect(Collectors.toList());
        List<Book> savedBooks = bookRepository.saveAll(books);
        return savedBooks.stream()
                         .map(this::convertToDTO)
                         .collect(Collectors.toList());
    }

    // Get book by ID with exception handling
    public BookDto getBookById(Integer id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book", "Id", id));
        return convertToDTO(book);
    }

    // Update an existing book with exception handling
    public BookDto updateBook(Integer id, BookDto bookDTO) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book", "Id", id));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());

        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    // Delete a book by ID with exception handling
    public void deleteBook(Integer id) {
        bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book", "Id", id));

        bookRepository.deleteById(id);
    }
   
    public List<Book> getBooksByName(String name) {
        return bookRepository.findByTitle(name);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> getBooksByPriceLessThan(Integer price) {
        return bookRepository.findByPriceLessThan(price);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
    
    public List<Book> getBooksByAuthorAndGenre(String author, String genre) {
        return bookRepository.findByAuthorAndGenre(author, genre);
    }
}
