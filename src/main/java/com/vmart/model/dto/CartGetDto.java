package com.vmart.model.dto;

public class CartGetDto {

	private Long id;
	private int quantity;
	private VegetableGetDto vegetable;
	
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
	
	public VegetableGetDto getVegetable() {
		return vegetable;
	}
	
	public void setVegetable(VegetableGetDto vegetable) {
		this.vegetable = vegetable;
	}
	
}
