package com.ynov.b3info;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/api/author", name = "LIBRARY-AUTHOR")
public interface AuthorRepository {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Author>> getAuthors();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Author>> getAuthor(@PathVariable("id") Integer id);

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Author>> searchAuthorsByName(@PathVariable("name") String name);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author newAuthor);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id);
}
