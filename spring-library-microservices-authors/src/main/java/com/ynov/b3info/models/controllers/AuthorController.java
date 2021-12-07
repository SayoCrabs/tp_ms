package com.ynov.b3info.models.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.b3info.models.Author;
import com.ynov.b3info.repositories.AuthorRepository;

@RestController
@RequestMapping("api/author")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Author>> getAuthors() {
		return ResponseEntity.ok(authorRepository.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Author>> getAuthor(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(authorRepository.findById(id));
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Author>> searchOrderByClientName(@PathVariable("name") String name) {
		return ResponseEntity.ok(authorRepository.findByAuthorNameStartingWith(name));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
		Author newAuthor = new Author();
		newAuthor.setAuthorName(author.getAuthorName());
		newAuthor.setBirthday(author.getBirthday());
		return ResponseEntity.ok(authorRepository.save(newAuthor));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author newAuthor) {
		Optional<Author> optAuthor = authorRepository.findById(id);
		if (optAuthor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Author author = optAuthor.get();
		author.setAuthorName(newAuthor.getAuthorName());
		author.setBirthday(newAuthor.getBirthday());
		return ResponseEntity.ok(authorRepository.save(author));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Author> deleteAuthor(@PathVariable("id") Integer id) {
		Optional<Author> optAuthor = authorRepository.findById(id);
		if (optAuthor.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Author author = optAuthor.get();
		authorRepository.delete(author);
		return ResponseEntity.ok(author);
	}
}
