package lv.venta.librarymanagementsystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.librarymanagementsystem.model.Book;
import lv.venta.librarymanagementsystem.repo.BookRepo;
import lv.venta.librarymanagementsystem.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	public BookServiceImpl(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public List<Book> searchBooks(String keyword) {
		if (keyword != null) {
			return bookRepo.search(keyword);
		}
		return bookRepo.findAll();
	}

	@Override
	public Book findBookById(Long id) throws Exception {
		return bookRepo.findById(id).orElseThrow(() -> new Exception("Book with this ID not found"));
	}

	@Override
	public void createBook(Book book) {
		bookRepo.save(book);
	}

	@Override
	public void updateBook(Book book) {
		bookRepo.save(book);
	}

	@Override
	public void deleteBook(Long id) throws Exception {
		Book book = bookRepo.findById(id).orElseThrow(() -> new Exception("Book with this ID not found"));
		bookRepo.deleteById(book.getId());
	}

}