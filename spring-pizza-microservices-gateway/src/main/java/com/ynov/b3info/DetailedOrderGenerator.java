package com.ynov.b3info;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedOrderGenerator {

	@Autowired
	private IngredientRepository ingredientRepository;

	public DetailedOrder generateDetailedOrder(Order order) {
		DetailedOrder detailedOrder = new DetailedOrder();
		detailedOrder.setId(order.getId());
		detailedOrder.setBaseType(order.getBaseType());
		detailedOrder.setCustomerAddress(order.getCustomerAddress());
		detailedOrder.setCustomerName(order.getCustomerName());
		detailedOrder.setIngredients(new HashSet<>());
		detailedOrder.setStatus(order.getStatus());
		Integer totalPrice = 0;
		for (Iterator iterator = order.getIngredients().iterator(); iterator.hasNext();) {
			Integer ingredient = (Integer) iterator.next();
			Optional<Ingredient> optIngredient = ingredientRepository.getIngredient(ingredient).getBody();
			if (optIngredient.isPresent()) {
				detailedOrder.getIngredients().add(optIngredient.get());
				totalPrice = totalPrice + optIngredient.get().getPrice();
			}
		}
		detailedOrder.setTotalPrice(totalPrice);
		return detailedOrder;
	}
}
