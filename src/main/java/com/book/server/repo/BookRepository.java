package com.book.server.repo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import com.book.server.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
    List<Book> findByTitle(String title);
    
    List<Book> findByGenre(String genre);
    
    List<Book> findByOrderByTitleAsc(); // This will order by title automatically

    @Query("SELECT b FROM Book b WHERE b.price < :price")
    List<Book> findByPriceLessThan(@Param("price") Integer price);
    
    List<Book> findByAuthor(String author);
    
    @Query("SELECT b FROM Book b WHERE b.author = :author AND b.genre = :genre")
    List<Book> findByAuthorAndGenre(@Param("author") String author, @Param("genre") String genre);

	
}
