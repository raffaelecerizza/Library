package it.unimib.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LibraryUnitTests {

	@Test
	public void test() {
		Book book = new Book();
		book.setId(100L);
		book.setAuthor("Autore prova 3");
		book.setTitle("Titolo prova 3");
		assert(book.getId() == 100L);
		assert(book.getAuthor().equals("Autore prova 3"));
		assert(book.getTitle().equals("Titolo prova 3"));
	}
	
}
