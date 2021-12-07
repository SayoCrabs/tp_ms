package com.ynov.b3info.models.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.b3info.models.Order;
import com.ynov.b3info.models.OrderStatus;
import com.ynov.b3info.repositories.OrderRepository;

@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Order>> getOrders() {
		return ResponseEntity.ok(orderRepository.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Order>> getOrder(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(orderRepository.findById(id));
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Order>> searchOrderByClientName(@PathVariable("name") String name) {
		return ResponseEntity.ok(orderRepository.findByCustomerNameStartingWith(name));
	}

	@RequestMapping(value = "/change-status/{id}/{new-status}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Order> changeOrderStatus(@PathVariable("id") Integer id,
			@PathVariable("new-status") OrderStatus newStatus) {
		Optional<Order> optOrder = orderRepository.findById(id);
		if (optOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Order order = optOrder.get();
		order.setStatus(newStatus);
		return ResponseEntity.ok(orderRepository.save(order));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order newOrder = new Order();
		newOrder.setBaseType(order.getBaseType());
		newOrder.setCustomerAddress(order.getCustomerAddress());
		newOrder.setCustomerName(order.getCustomerName());
		newOrder.setIngredients(order.getIngredients());
		newOrder.setStatus(OrderStatus.ORDERED);
		return ResponseEntity.ok(orderRepository.save(newOrder));
	}
}
