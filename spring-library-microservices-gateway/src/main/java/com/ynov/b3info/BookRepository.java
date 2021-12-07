package com.ynov.b3info;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/api/book", name = "LIBRARY-BOOK")
public interface BookRepository {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Book>> getBooks();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Book>> getBook(@PathVariable("id") Integer id);

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Book>> searchBooksByAuthor(@PathVariable("name") String name);
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Book> createBook(@RequestBody Book Book);

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book newBook);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id);
	
}
