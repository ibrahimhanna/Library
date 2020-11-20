package com.library.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	
	List<Book> findByUser(User user);
	
	
	List<Book> findByBooknameContaining(String bookname);
	
	
	
	
}
