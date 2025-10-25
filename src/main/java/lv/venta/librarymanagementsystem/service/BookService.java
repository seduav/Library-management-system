package lv.venta.librarymanagementsystem.service;

import java.util.List;
import lv.venta.librarymanagementsystem.model.Book;

public interface BookService {

	public List<Book> findAllBooks();
	
	public List<Book> searchBooks(String keyword);

	public Book findBookById(Long id) throws Exception;

	public void createBook(Book book);

	public void updateBook(Book book);

	public void deleteBook(Long id) throws Exception;

}