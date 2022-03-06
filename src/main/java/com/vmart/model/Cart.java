package com.vmart.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;

	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="vegetable_id", referencedColumnName="id")
	private Vegetable vegetable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vegetable getVegetable() {
		return vegetable;
	}

	public void setVegetable(Vegetable vegetable) {
		this.vegetable = vegetable;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", quantity=" + quantity + ", user=" + user + ", vegetable=" + vegetable + "]";
	}

}
