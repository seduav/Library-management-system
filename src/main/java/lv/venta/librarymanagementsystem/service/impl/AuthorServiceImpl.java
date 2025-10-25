package lv.venta.librarymanagementsystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.librarymanagementsystem.model.Author;
import lv.venta.librarymanagementsystem.repo.AuthorRepo;
import lv.venta.librarymanagementsystem.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepo authorRepo;

	public AuthorServiceImpl(AuthorRepo authorRepo) {
		this.authorRepo = authorRepo;
	}

	@Override
	public List<Author> findAllAuthors() {
		return authorRepo.findAll();
	}

	@Override
	public Author findAuthorById(Long id) throws Exception {
		return authorRepo.findById(id).orElseThrow(() -> new Exception("Author with this ID not found"));
	}

	@Override
	public void createAuthor(Author author) {
		authorRepo.save(author);
	}

	@Override
	public void updateAuthor(Author author) {
		authorRepo.save(author);
	}

	@Override
	public void deleteAuthor(Long id) throws Exception {
		Author author = authorRepo.findById(id).orElseThrow(() -> new Exception("Author with this ID not found"));
		authorRepo.deleteById(author.getId());
	}

}