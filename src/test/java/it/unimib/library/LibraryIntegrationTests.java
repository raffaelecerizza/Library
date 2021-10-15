package it.unimib.library;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LibraryIntegrationTests {
		
	@Autowired
	private BookService service;
	
	@Test
	public void test() {
		Book book1 = new Book();
		book1.setAuthor("Autore prova 5");
		book1.setTitle("Titolo prova 5");
		//System.out.println("Libro 1 id: " + book1.getId());
		service.save(book1);

		Page<Book> page = service.listAll(1, "id", "dsc");
		List<Book> listBooks = page.getContent();
		//System.out.println("Lista: " + listBooks.toString());
		
		Book book2 = listBooks.get(0);
		Long id2 = book2.getId();
		assert(book2.getAuthor().equals("Autore prova 5"));
		assert(book2.getTitle().equals("Titolo prova 5"));
		//System.out.println("Libro 2: " + book2.toString());
		//System.out.println("Libro 2 id: " + book2.getId());
		//System.out.println("Libro 2 autore: " + book2.getAuthor());
		//System.out.println("Libro 2 titolo: " + book2.getTitle());
		
		service.delete(id2);
		page = service.listAll(1, "id", "dsc");
		listBooks = page.getContent();
		Book book3 = listBooks.get(0);
		Long id3 = book3.getId();
		assert(book3.getId() != id2);
		
	}
	
}
