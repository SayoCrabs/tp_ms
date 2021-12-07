package com.ynov.b3info.models;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

	public Iterable<Ingredient> findByNameStartingWith(String name);
}
