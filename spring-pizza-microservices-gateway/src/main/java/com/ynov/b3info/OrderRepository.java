package com.ynov.b3info;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/api/order", name = "PIZZA-ORDERS")
public interface OrderRepository {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Order>> getOrders();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Order>> getOrder(@PathVariable("id") Integer id);

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Iterable<Order>> searchOrderByClientName(@PathVariable("name") String name);

	@RequestMapping(value = "/change-status/{id}/{new-status}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Order> changeOrderStatus(@PathVariable("id") Integer id,
			@PathVariable("new-status") OrderStatus newStatus);
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Order> createOrder(@RequestBody Order order);
}
