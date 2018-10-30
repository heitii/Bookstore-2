package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userrepository;
	
	/** Test for search by username **/
	@Test
	public void findByUsernameShouldReturnUser() {
		User users = userrepository.findByUsername("user");
		assertThat(users.getUsername()).isEqualTo("user");
	}
	
	/** Creates a new test user **/
	@Test
	public void createNewUser() {
		User user = new User("kayttelija", "$2a$10$uFe00PCer9y40dLcBUJ0wuMPC8HgK.Vlsp4DXYkilPT7knci4eQL2", "kayttelija@kayttelija.fi", "USER");
		userrepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	/** Test that creates a new test user and deletes it **/
	@Test
	public void deleteUser() {
		User user = new User("kayttaja", "$2a$10$7DKtUpFV0SS9xTFL6.SVouob3EpYzAfu1ZJYndQKRNQCHGo8ntExa", "kayttaja@kayttaja.fi", "USER");
		userrepository.save(user);
		
		Long id = user.getId();
		userrepository.deleteById(id);
		
		Optional<User> deletedUser = userrepository.findById(id);
		assertThat(deletedUser).isEmpty();
	}
}