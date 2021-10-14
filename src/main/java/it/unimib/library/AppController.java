package it.unimib.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private BookService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return viewPage(model, 1, "author", "asc");
	}
	
	@RequestMapping("/page/{pageNum}")
	public String viewPage(Model model, 
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
		
		Page<Book> page = service.listAll(pageNum, sortField, sortDir);
		
		List<Book> listBooks = page.getContent();
		
		model.addAttribute("currentPage", pageNum);		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listBooks", listBooks);
		
		return "index";
	}	
	
	@RequestMapping("/new")
	public String showNewBookForm(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		
		return "new_book";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute("book") Book book) {
		service.save(book);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditBookForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_book");
		
		Book book = service.get(id);
		mav.addObject("book", book);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteBook(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}

}
