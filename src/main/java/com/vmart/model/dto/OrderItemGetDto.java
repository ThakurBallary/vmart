package com.vmart.model.dto;

public class OrderItemGetDto {

	private Long id;
	private int quantity;
	private double unitPrice;
	private String seller;
	private String vegetable;
	
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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
		
}

// TODO CartPutDto

