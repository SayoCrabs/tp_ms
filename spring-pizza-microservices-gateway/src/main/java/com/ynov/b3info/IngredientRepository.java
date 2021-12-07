package com.ynov.b3info;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/api/ingredient", name = "PIZZA-INGREDIENTS")
public interface IngredientRepository {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Ingredient>> getIngredients();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Ingredient>> getIngredient(@PathVariable("id") Integer id);

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Ingredient>> searchIngredientsByName(@PathVariable("name") String name);
	
	@RequestMapping(value = "/{name}/{price}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Ingredient> createIngredient(@PathVariable("name") String name, @PathVariable("price") Integer price);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient newIngredient);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Integer id);
}
