package com.book.serviceImpl;

import com.book.entity.Book;
import com.book.payload.BookDto;
import com.book.repo.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    @Autowired
    private BookRepository bookRepository;

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

    // Get book by ID
    public BookDto getBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDTO(book);
    }

    // Update an existing book
    public BookDto updateBook(Integer id, BookDto bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    // Delete a book by ID
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
