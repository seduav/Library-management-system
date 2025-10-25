package lv.venta.librarymanagementsystem.repo;

import org.springframework.data.repository.CrudRepository;
import lv.venta.librarymanagementsystem.model.Authority;

public interface AuthorityRepo extends CrudRepository<Authority, Integer>{

}