package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

		@Autowired
		private BookRepository bookrepository;
		
		/** Test for search by book title **/
		@Test
		public void findByTitleShouldReturnBook() {
			List<Book> books = bookrepository.findByTitle("Harry Potter and the Philosophers Stone");
			
			assertThat(books).hasSize(1);
			assertThat(books.get(0).getAuthor()).isEqualTo("J.K. Rowling");
		}
		
		/** Creates a new test book **/
		@Test
		public void createNewBook() {
			Book book = new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999, "0-7475-4215-5", 24.95, new Category("Adventure"));
			bookrepository.save(book);
			assertThat(book.getId()).isNotNull();
		}
		
		/** Creates a new test book and deletes it **/
		@Test 
		public void deleteBook() {
			Book book = new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", 2000, "0-7475-4624-X", 24.95, new Category("Young Adult"));
			bookrepository.save(book);
			
			Long id = book.getId();
			bookrepository.deleteById(id);
			
			Optional<Book> deletedBook = bookrepository.findById(id);
			assertThat(deletedBook).isEmpty();
		}
}
