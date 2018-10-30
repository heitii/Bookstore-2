package hh.swd20.bookstore.webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/** Kirjautuu sisään **/
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	/** Listaa kirjat tietokannasta **/
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	/** RESTful palvelu kaikkien kirjojen hakuun **/
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	/** RESTful palvelu kirjan hakuun id:n perusteella  **/
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}
	
	/** Palauttaa tyhjän lomakkeen uuden kirjan lisäämiseen **/
	@RequestMapping(value="/addbook")
	  public String addBook(Model model){
	    model.addAttribute("book", new Book());
	    model.addAttribute("categorylist", categoryRepository.findAll());
	       return "addbook";
	  }     
	/** Tallentaa lomakkeeseen tallennetut tiedot **/    
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	  public String save(Book book){
		bookRepository.save(book);
	    	return "redirect:booklist";
	  }
	/** Poistaa valitulla id:llä olevan kirjan **/
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	/** Editoi/muuttaa valitulla id:llä olevaa kirjaa **/
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categorylist", categoryRepository.findAll());
		return "editbook";
	}
}
