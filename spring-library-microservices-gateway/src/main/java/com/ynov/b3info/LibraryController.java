package com.ynov.b3info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	@Autowired
	private BookRepository BookRepository;
	@Autowired
	private AuthorRepository AuthorRepository;

	@RequestMapping(value = "/book", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Book>> getBooks() {
		return BookRepository.getBooks();
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Book>> getBook(@PathVariable("id") Integer id) {
		return BookRepository.getBook(id);
	}

	@RequestMapping(value = "/book/search/{author}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Book>> searchBooksByAuthor(@PathVariable("name") String name) {
		return BookRepository.searchBooksByAuthor(name);
	}
	
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook) {
		return BookRepository.updateBook(id, newBook);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id) {
		return BookRepository.deleteBook(id);
	}
	
	//---------------------------------------------------------------
	
	@RequestMapping(value = "/author", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Author>> getauthors() {
		return AuthorRepository.getAuthors();
	}
	
	@RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Author>> getauthor(@PathVariable("id") Integer id) {
		return AuthorRepository.getAuthor(id);
	}

	@RequestMapping(value = "/author/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Author>> searchAuthorsByName(@PathVariable("name") String name) {
		return AuthorRepository.searchAuthorsByName(name);
	}
	
	
	@RequestMapping(value = "/author/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author newauthor) {
		return AuthorRepository.updateAuthor(id, newauthor);
	}
	
	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Author> deleteauthor(@PathVariable("id") Integer id) {
		return AuthorRepository.deleteAuthor(id);
	}
	


}
