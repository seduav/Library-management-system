package lv.venta.librarymanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.librarymanagementsystem.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}