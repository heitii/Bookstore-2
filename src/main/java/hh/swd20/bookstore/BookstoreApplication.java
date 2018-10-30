package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;
import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookrepository, CategoryRepository categoryrepository, UserRepository userrepository) {
		return (args) -> {
			log.info("save a couple of books");
			categoryrepository.save(new Category("Fantasy"));
			categoryrepository.save(new Category("Historical"));
			categoryrepository.save(new Category("Horror"));
			
			// Lisätään pari käyttäjää
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@email.fi", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@email.fi", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);
			
			bookrepository.save(new Book("Harry Potter and the Philosophers Stone", "J.K. Rowling", 1997, "0-7475-3269-9", 24.95, categoryrepository.findByName("Fantasy").get(0)));
			bookrepository.save(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, "0-7475-3849-2", 24.95, categoryrepository.findByName("Fantasy").get(0)));
			
			log.info("fetch all books");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
