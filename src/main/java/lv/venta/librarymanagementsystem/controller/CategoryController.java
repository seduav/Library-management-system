package lv.venta.librarymanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lv.venta.librarymanagementsystem.model.Category;
import lv.venta.librarymanagementsystem.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/categories")
	public String findAllCategories(Model model) {
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("categories", categories);
		return "list-categories";
	}

	@GetMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) throws Exception {
		Category category = categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		return "list-category";
	}

	@GetMapping("/add-category")
	public String showCreateForm(Category category, Model model) {
		try {
			return "add-category";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add-category")
	public String createCategory(Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}
		categoryService.createCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		return "redirect:/categories";
	}

	@GetMapping("/update-category/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			Category category = categoryService.findCategoryById(id);
		    model.addAttribute("category", category);
		    return "update-category";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update-category/{id}")
	public String updateCategory(@PathVariable("id") Long id, Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			category.setId(id);
			return "update-category";
		}
		categoryService.updateCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		return "redirect:/categories";
	}

	@GetMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			categoryService.deleteCategory(id);
		    model.addAttribute("category", categoryService.findAllCategories());
		    return "redirect:/categories";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

}