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
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private DetailedOrderGenerator detailedOrderGenerator;

	@RequestMapping(value = "/ingredient", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Ingredient>> getIngredients() {
		return ingredientRepository.getIngredients();
	}
	
	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Ingredient>> getIngredient(@PathVariable("id") Integer id) {
		return ingredientRepository.getIngredient(id);
	}

	@RequestMapping(value = "/ingredient/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Ingredient>> searchIngredientsByName(@PathVariable("name") String name) {
		return ingredientRepository.searchIngredientsByName(name);
	}
	
	@RequestMapping(value = "/ingredient/{name}/{price}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Ingredient> createIngredient(@PathVariable("name") String name, @PathVariable("price") Integer price) {
		return ingredientRepository.createIngredient(name, price);
	}
	
	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") Integer id, @RequestBody Ingredient newIngredient) {
		return ingredientRepository.updateIngredient(id, newIngredient);
	}
	
	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") Integer id) {
		return ingredientRepository.deleteIngredient(id);
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Order>> getOrders() {
		return orderRepository.getOrders();
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<DetailedOrder> getOrder(@PathVariable("id") Integer id) {
		Optional<Order> optOrder = orderRepository.getOrder(id).getBody();
		if (optOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(detailedOrderGenerator.generateDetailedOrder(optOrder.get()));
	}

	@RequestMapping(value = "/order/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Order>> searchOrderByClientName(@PathVariable("name") String name) {
		return orderRepository.searchOrderByClientName(name);
	}

	@RequestMapping(value = "/order/change-status/{id}/{new-status}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Order> changeOrderStatus(@PathVariable("id") Integer id,
			@PathVariable("new-status") OrderStatus newStatus) {
		return orderRepository.changeOrderStatus(id, newStatus);
	}
}
