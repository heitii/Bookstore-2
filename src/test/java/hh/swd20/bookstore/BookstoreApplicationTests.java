package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.webcontrol.BookController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {
	
	@Autowired
	private BookController bookcontroller;

	/** Smoketest for bookcontroller **/
	@Test
	public void contextLoads() throws Exception {
		assertThat(bookcontroller).isNotNull();
	}

}
