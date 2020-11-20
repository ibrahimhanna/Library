package com.library.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	
	
	public boolean saveBook(Book book) {
		bookRepository.save(book);
		return true;
	}
	
	
	public List<Book> fetchBooks(int start,int end){
	
	 Page<Book> list= bookRepository.findAll(PageRequest.of(start, end));
	 
	 return list.getContent();
	 
	 
	}
	
	
	public List<Book> fetchMyBooks(User user){
		
		List<Book> list= bookRepository.findByUser(user);
         return list;
	}
	
	public List<Book> bookSearch(String bookname){
		return bookRepository.findByBooknameContaining(bookname);
	}
	
	
	public long getBooksCount() {
		
		return bookRepository.count();
	}
	
	
	
}
