package lv.venta.librarymanagementsystem.service;

import java.util.List;
import lv.venta.librarymanagementsystem.model.Category;

public interface CategoryService {

	public List<Category> findAllCategories();

	public Category findCategoryById(Long id) throws Exception;

	public void createCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Long id) throws Exception;

}