package lv.venta.librarymanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.venta.librarymanagementsystem.model.Publisher;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}