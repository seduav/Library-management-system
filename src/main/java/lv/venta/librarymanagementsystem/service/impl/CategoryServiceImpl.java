package lv.venta.librarymanagementsystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.librarymanagementsystem.model.Category;
import lv.venta.librarymanagementsystem.repo.CategoryRepo;
import lv.venta.librarymanagementsystem.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public CategoryServiceImpl(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		return categoryRepo.findById(id).orElseThrow(() -> new Exception("Category with this ID not found"));
	}

	@Override
	public void createCategory(Category category) {
		categoryRepo.save(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryRepo.save(category);
	}

	@Override
	public void deleteCategory(Long id) throws Exception {
		Category category = categoryRepo.findById(id).orElseThrow(() -> new Exception("Category with this ID not found"));
		categoryRepo.deleteById(category.getId());
	}

}