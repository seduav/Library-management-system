package lv.venta.librarymanagementsystem.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.librarymanagementsystem.model.User;

public interface UserRepo extends CrudRepository<User, Integer>{

	User findByUsername(String username);

}