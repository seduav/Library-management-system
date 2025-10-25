package lv.venta.librarymanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lv.venta.librarymanagementsystem.model.Book;
import lv.venta.librarymanagementsystem.service.AuthorService;
import lv.venta.librarymanagementsystem.service.BookService;
import lv.venta.librarymanagementsystem.service.CategoryService;
import lv.venta.librarymanagementsystem.service.PublisherService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService,
			PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}

	@GetMapping("/books")
	public String findAllBooks(Model model) {
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("books", books);
		return "list-books";
	}

	@GetMapping("/search-book")
	public String searchBook(@Param("keyword") String keyword, Model model) {
		List<Book> books = bookService.searchBooks(keyword);
		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		return "list-books";
	}

	@GetMapping("/search-book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) throws Exception {
		Book book = bookService.findBookById(id);
		model.addAttribute("book", book);
		return "list-book";
	}

	@GetMapping("/add-book")
	public String showCreateForm(Book book, Model model) {
		try {
			model.addAttribute("categories", categoryService.findAllCategories());
		    model.addAttribute("authors", authorService.findAllAuthors());
		    model.addAttribute("publishers", publisherService.findAllPublishers());
		    return "add-book";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add-book")
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}
		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/update-book/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			Book book = bookService.findBookById(id);
			model.addAttribute("categories", categoryService.findAllCategories());
		    model.addAttribute("authors", authorService.findAllAuthors());
		    model.addAttribute("publishers", publisherService.findAllPublishers());
		    model.addAttribute("book", book);
		    return "update-book";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}
		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			bookService.deleteBook(id);
		    model.addAttribute("book", bookService.findAllBooks());
		    return "redirect:/books";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

}