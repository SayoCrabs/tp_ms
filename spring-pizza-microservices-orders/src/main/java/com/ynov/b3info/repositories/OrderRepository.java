package com.ynov.b3info.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ynov.b3info.models.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	Iterable<Order> findByCustomerNameStartingWith(String name);

}
