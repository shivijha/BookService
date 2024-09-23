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
		if (paginatedBooks.isEmpty()) {
			throw new BookNotFoundException("No books are available");
		}
		return paginatedBooks.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Save a new book
	public List<BookDto> saveBooks(List<BookDto> bookDtos) {
		List<Book> books = bookDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
		List<Book> savedBooks = bookRepository.saveAll(books);
		return savedBooks.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Get book by ID with exception handling
	public BookDto getBookById(Integer id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
		return convertToDTO(book);
	}

	// Update an existing book with exception handling
	public BookDto updateBook(Integer id, BookDto bookDTO) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setPrice(bookDTO.getPrice());

		Book updatedBook = bookRepository.save(book);
		return convertToDTO(updatedBook);
	}

	// Delete a book by ID with exception handling
	public void deleteBook(Integer id) {
		bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

		bookRepository.deleteById(id);
	}

	public List<Book> getBooksByName(String name) {
		List<Book> books = bookRepository.findByTitle(name);
		if (books.isEmpty()) {
			throw new BookNotFoundException("No books found with title: " + name);
		}
		return books;
	}

	public List<Book> getBooksByGenre(String genre) {
		List<Book> books = bookRepository.findByGenre(genre);
		if (books.isEmpty()) {
			throw new BookNotFoundException("No books found in genre: " + genre);
		}
		return books;
	}

	public List<Book> getBooksByPriceLessThan(Integer price) {
		List<Book> books = bookRepository.findByPriceLessThan(price);
		if (books.isEmpty()) {
			throw new BookNotFoundException("No books found with price less than: " + price);
		}
		return books;
	}

	public List<Book> getBooksByAuthor(String author) {
		List<Book> books = bookRepository.findByAuthor(author);
		if (books.isEmpty()) {
			throw new BookNotFoundException("No books found by author: " + author);
		}
		return books;
	}

	public List<Book> getBooksByAuthorAndGenre(String author, String genre) {
		List<Book> books = bookRepository.findByAuthorAndGenre(author, genre);
		if (books.isEmpty()) {
			throw new BookNotFoundException("No books found by author: " + author + " in genre: " + genre);
		}
		return books;
	}
	
	public List<Book> getBookOrderByTitle(){
		List<Book> books = bookRepository.findBookOrderedByTitle();
		if(books.isEmpty()) {
			throw new BookNotFoundException("bookList is empty");
		}
		return books;
	}

}
