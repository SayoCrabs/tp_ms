package com.ynov.b3info.models;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity(name = "pizzaorder")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    private String customerName;
    private String customerAddress;
    @ElementCollection
    @CollectionTable(name = "order_ingredients", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "ingredients")
    private Set<Integer> ingredients;
    private OrderStatus status;
    private DoughType doughType;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Set<Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Integer> ingredients) {
		this.ingredients = ingredients;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public DoughType getBaseType() {
		return doughType;
	}

	public void setBaseType(DoughType baseType) {
		this.doughType = baseType;
	}
    
    
}
