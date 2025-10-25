package lv.venta.librarymanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.librarymanagementsystem.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}