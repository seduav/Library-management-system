package lv.venta.librarymanagementsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import lv.venta.librarymanagementsystem.model.Author;
import lv.venta.librarymanagementsystem.model.Book;
import lv.venta.librarymanagementsystem.model.Category;
import lv.venta.librarymanagementsystem.model.CoverType;
import lv.venta.librarymanagementsystem.model.Language;
import lv.venta.librarymanagementsystem.model.Publisher;
import lv.venta.librarymanagementsystem.model.Authority;
import lv.venta.librarymanagementsystem.model.User;
import lv.venta.librarymanagementsystem.repo.AuthorityRepo;
import lv.venta.librarymanagementsystem.repo.UserRepo;
import lv.venta.librarymanagementsystem.service.BookService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService, AuthorityRepo authRepo, UserRepo userRepo) {
		return (args) -> {
			
			Book book = new Book("Isbn-1", "Name-1", "Description-1", CoverType.Hardcase, Language.Other);
			Author author = new Author("Author-1", "Description-1");
			Category category = new Category("Fiction");
			Publisher publisher = new Publisher("Publisher-1");

			book.addAuthors(author);
			book.addCategories(category);
			book.addPublishers(publisher);

			bookService.createBook(book);

			Book book1 = new Book("Isbn-2", "Name-2", "Description-2", CoverType.Hardcase, Language.Latvian);
			Author author1 = new Author("Author-2", "Description-2");
			Category category1 = new Category("History");
			Publisher publisher1 = new Publisher("Publisher-2");

			book1.addAuthors(author1);
			book1.addCategories(category1);
			book1.addPublishers(publisher1);

			bookService.createBook(book1);

			Book book2 = new Book("Isbn-3", "Name-3", "Description-3", CoverType.Paperback, Language.English);
			Author author2 = new Author("Author-3", "Description-3");
			Category category2 = new Category("Biography");
			Publisher publisher2 = new Publisher("Publisher-3");

			book2.addAuthors(author2);
			book2.addCategories(category2);
			book2.addPublishers(publisher2);

			bookService.createBook(book2);
			
			Authority a1 = new Authority("ADMIN");
			Authority a2 = new Authority("USER");
			authRepo.save(a1);
			authRepo.save(a2);

			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			User u1 = new User("admin", encoder.encode("admin"), a1);
			User u2 = new User("user", encoder.encode("123"), a2);
			User u3 = new User("useradmin", encoder.encode("qwerty"), a1, a2);
			userRepo.save(u1);
			userRepo.save(u2);
			userRepo.save(u3);

			a1.addUser(u1);
			a1.addUser(u3);
			authRepo.save(a1);
			a2.addUser(u3);
			a2.addUser(u2);
			authRepo.save(a2);
		};
	}
	
}