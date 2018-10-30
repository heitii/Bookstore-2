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
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

		@Autowired
		private CategoryRepository categoryrepository;
		
		/** Test for search by category name **/
		@Test
		public void findByNameShouldReturnCategory() {
			List<Category> categories = categoryrepository.findByName("Fantasy");
			assertThat(categories).hasSize(1);
		}
		
		/** Creates a new test category **/
		@Test
		public void createNewCategory() {
			Category category = new Category("Sci-Fi");
			categoryrepository.save(category);
			assertThat(category.getCategoryid()).isNotNull();
		}
}