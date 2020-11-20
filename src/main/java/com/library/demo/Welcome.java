package com.library.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Welcome {

	@Autowired
	UserService userService;
	
	
	@Autowired
	LoginService loginService;
	
	
	@Autowired
	CountryService countryService;
	
	
	@Autowired
	BookService bookService;
	
	
	 @GetMapping("/welcome")
	public String welcome() {
		
		return "welcome";
	}
	
	 @RequestMapping("/register")
	 public String register(Model model) {
		 model.addAttribute("user", new User());
		 model.addAttribute("countries", countryService.getCountries());
		 return "/register";
	 }
	 
	 @PostMapping("/register_form")
	 public String registerForm(@ModelAttribute("user") User user,Model model) {
		 userService.saveUser(user);
		 
		 Login login = new Login();
		 login.setEmail(user.getEmail());
		 model.addAttribute("login", login);
		
		 return "/final-register";
	 }
	 
	 @PostMapping("/final_register")
	 public String  final_register(@ModelAttribute("login") Login login,Model model) {
		 loginService.saveLogin(login);
		 
		
		 return "sucess";
	 }
	

	 @RequestMapping("/forgetpassword")
	 public String forgetpassword() {
		 return "forgetpassword";
	 }
	 
	 
	 @RequestMapping("/hello")
	   @ResponseBody
	   public String hello() {
	      return "Hello Spring Boot";
	   }
	 
	 
	 @RequestMapping("/person")
	 @ResponseBody
	   public List<User> person() {
	    
		 User person = new User();
		 person.setId(1);
		 person.setFirstname("Ibrahim");
		 person.setLastname("Hanna");
		 person.setAccount_type(1);
		 person.setCountry(1);
		 person.setAddress("30 mehelmy street");
		 
		 
		 ArrayList<User> persons = new ArrayList<User>();
		 persons.add(person);
		 
		 return persons;
		 
	   }
	 
	 
	 @RequestMapping("/persons")
	   @ResponseBody
	   public List<User> loadPersons() {
		 
		 return userService.fetchPersons();
	 }
	 
	 
	 
	 @RequestMapping("/login")
	   @ResponseBody
	   public Login login(@RequestParam String username,@RequestParam String password) {
		

		 return loginService.login(username, password);

	 }
	 
	 @GetMapping("/index")
	 public String index(Model model,HttpSession session ) {
 String messages = (String) session.getAttribute("userID");


				if (messages!=null) {
					 User userT = userService.getUser((String)session.getAttribute("userID"));
					 model.addAttribute("accountType", userT.getAccount_type());
					 model.addAttribute("bookscount",bookService.getBooksCount());
					 model.addAttribute("search", new Search());
					 List<Book> books = bookService.fetchBooks(0,5);
					 model.addAttribute("books", books);
					return "sucess"; 
				}else {
		 model.addAttribute("user", new Login());
			
		 return "index";
				}
	 }
	 
	 @PostMapping("/loginn")
	 public String loginForm(@ModelAttribute("user") Login user , Model model,HttpSession session) {
		 Login loginner = null;
				
	 
		 try {
			 loginner = loginService.login(user.getUsername(), user.getPassword());
              session.setAttribute("userID", loginner.getEmail());
		 }
		 catch (Exception e) {
			 loginner = null;
		}
		 
		 if(loginner!=null) {
			 User userT = userService.getUser(loginner.getEmail());
			 model.addAttribute("search", new Search());
			 model.addAttribute("accountType", userT.getAccount_type());
			 model.addAttribute("bookscount",bookService.getBooksCount());
			 model.addAttribute("url","randombooks");
			 List<Book> books = bookService.fetchBooks(0,5);
			 model.addAttribute("books", books);
			 
			 return "sucess";
		 }
		 
		 else {
			 model.addAttribute("user", new Login());
			 return "index";
		 }
		 
		 
	     
	 }
	 
	 
	 
}
