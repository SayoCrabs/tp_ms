package com.ynov.b3info.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ynov.b3info.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

	Iterable<Author> findByAuthorNameStartingWith(String name);

}
