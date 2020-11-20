package com.library.demo;





import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController
 {

	@Autowired
     BookService bookService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/create-book")
	public String create_book(Model model){
		
		    model.addAttribute("book", new Book());
		   
	            return "bookpublish";  

	}
	
	
	@RequestMapping("/destroy")
	public String destroySession(Model model,HttpSession request) {
		request.invalidate();
		 model.addAttribute("user", new Login());
		return "index";
	}
	
	
	@PostMapping("/publish-book")
	public String publishbookAction(@ModelAttribute("book") Book book , Model model, HttpServletRequest request) {
		 User userT = userService.getUser(request.getSession().getAttribute("userID").toString());
		book.setUser(userT);
		
		
		 String dateStr = book.getPublish_date().toString();
         
         DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          
         // parse the date string into Date object
         try {
			Date date = srcDf.parse(dateStr);
			book.setPublish_date(date+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		    
		}
		
		bookService.saveBook(book);
		
		 model.addAttribute("accountType", userT.getAccount_type());
		 model.addAttribute("bookscount",bookService.getBooksCount());
		 model.addAttribute("url","mybooks");	
		 List<Book> books = bookService.fetchBooks(1,5);
			 model.addAttribute("books", books);
		return "sucess";
	}
	
	
	@GetMapping("/books")
	public String fetchBooks(@RequestParam int start,@RequestParam int end,Model model, HttpSession request) {
		try{
	
		 User userT =userService.getUser(request.getAttribute("userID").toString());
		 model.addAttribute("accountType", userT.getAccount_type());
		 model.addAttribute("bookscount",bookService.getBooksCount());
		 model.addAttribute("url","randombooks");
		 List<Book> books = bookService.fetchBooks(0,end);
		 model.addAttribute("books", books);
		 
		 return "sucess";
		}
		catch (Exception e) {
			 model.addAttribute("user", new Login());
			 return "index";
		}
	}
	
	
	
	@GetMapping("/mybooks")
	public String myBooks(Model model,HttpSession session) {
		 User userT = userService.getUser((String)session.getAttribute("userID"));
		 model.addAttribute("accountType", userT.getAccount_type());
		 model.addAttribute("url","mybooks");
		 model.addAttribute("search", new Search());
		 List<Book> books = bookService.fetchMyBooks(userT);
		 model.addAttribute("books", books);
		return "sucess";
	}
	
	
	@PostMapping("/booksearch")
	public String bookSearch(@ModelAttribute(name="search") Search search,Model model,HttpSession session) {
		
		 User userT = userService.getUser((String)session.getAttribute("userID"));
		 model.addAttribute("accountType", userT.getAccount_type());
		 model.addAttribute("url","mybooks");
		 List<Book> books = bookService.bookSearch(search.getSearchtext());
		 model.addAttribute("books", books);
		
		
		return "sucess";
	}
	
	
	
	
	
}
