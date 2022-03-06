package com.vmart.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OrderItemSetDto {

	@Min(value=1, message="Min. Quantity 1 kg")
	private int quantity;	
	
	@Min(value=1, message="Invalid")
	@Max(value=1000, message="Invalid")
	private Long vegetableId;
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Long getVegetableId() {
		return vegetableId;
	}
	
	public void setVegetableId(Long vegetableId) {
		this.vegetableId = vegetableId;
	}
	
}

// TODO CartPutDto

