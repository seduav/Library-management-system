package lv.venta.librarymanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lv.venta.librarymanagementsystem.model.Author;
import lv.venta.librarymanagementsystem.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("/authors")
	public String findAllAuthors(Model model) {
		List<Author> authors = authorService.findAllAuthors();
		model.addAttribute("authors", authors);
		return "list-authors";
	}

	@GetMapping("/author/{id}")
	public String findAuthorById(@PathVariable("id") Long id, Model model) throws Exception {
		Author author = authorService.findAuthorById(id);
		model.addAttribute("author", author);
		return "list-author";
	}

	@GetMapping("/add-author")
	public String showCreateForm(Author author, Model model) {
		try {
			return "add-author";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add-author")
	public String createAuthor(Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-author";
		}
		authorService.createAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

	@GetMapping("/update-author/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			Author author = authorService.findAuthorById(id);
		    model.addAttribute("author", author);
		    return "update-author";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update-author/{id}")
	public String updateAuthor(@PathVariable("id") Long id, Author author, BindingResult result, Model model) {
		if (result.hasErrors()) {
			author.setId(id);
			return "update-author";
		}
		authorService.updateAuthor(author);
		model.addAttribute("author", authorService.findAllAuthors());
		return "redirect:/authors";
	}

	@GetMapping("/remove-author/{id}")
	public String deleteAuthor(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			authorService.deleteAuthor(id);
		    model.addAttribute("author", authorService.findAllAuthors());
		    return "redirect:/authors";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

}