package com.vmart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String seller;

	private String vegetable;

	private double price;

	private int quantity;
	
	@ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
	@JsonIgnoreProperties("orderItems")
	private Order order;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getVegetable() {
		return vegetable;
	}

	public void setVegetable(String vegetable) {
		this.vegetable = vegetable;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", seller=" + seller + ", vegetable=" + vegetable + ", price="
				+ price + ", quantity=" + quantity + ", order=" + order + "]";
	}

}
