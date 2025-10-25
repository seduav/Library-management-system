package lv.venta.librarymanagementsystem.service;

import java.util.List;
import lv.venta.librarymanagementsystem.model.Author;

public interface AuthorService {

	public List<Author> findAllAuthors();

	public Author findAuthorById(Long id) throws Exception;

	public void createAuthor(Author author);

	public void updateAuthor(Author author);

	public void deleteAuthor(Long id) throws Exception;

}