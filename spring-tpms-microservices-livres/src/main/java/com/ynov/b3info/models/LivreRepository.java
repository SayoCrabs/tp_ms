package com.ynov.b3info.models;

import org.springframework.data.repository.CrudRepository;

public interface LivreRepository extends CrudRepository<Livre, Integer> {
	public Iterable<Livre> findByAuteur(String name);
}
