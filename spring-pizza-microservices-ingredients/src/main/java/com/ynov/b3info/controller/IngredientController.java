package com.ynov.b3info.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.b3info.models.Ingredient;
import com.ynov.b3info.models.IngredientRepository;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {
	
	@Autowired
	private IngredientRepository ingredientRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Ingredient>> getIngredients() {
		return ResponseEntity.ok(ingredientRepository.findAll());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Ingredient>> getIngredient(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ingredientRepository.findById(id));
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Ingredient>> searchIngredientsByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(ingredientRepository.findByNameStartingWith(name));
	}
	
	@RequestMapping(value = "/{name}/{price}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Ingredient> createIngredient(@PathVariable("name") String name, @PathVariable("price") Integer price) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(name);
		ingredient.setPrice(price);
		return ResponseEntity.ok(ingredientRepository.save(ingredient));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient newIngredient) {
		Optional<Ingredient> optIngredient = ingredientRepository.findById(id);
		if (optIngredient.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Ingredient ingredient = optIngredient.get();
		ingredient.setName(newIngredient.getName());
		ingredient.setPrice(newIngredient.getPrice());
		return ResponseEntity.ok(ingredientRepository.save(ingredient));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Integer id) {
		Optional<Ingredient> optIngredient = ingredientRepository.findById(id);
		if (optIngredient.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Ingredient ingredient = optIngredient.get();
		ingredientRepository.delete(ingredient);
		return ResponseEntity.ok(ingredient);
	}
}
