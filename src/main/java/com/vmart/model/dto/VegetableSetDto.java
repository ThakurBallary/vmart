package com.vmart.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class VegetableSetDto {
	
	private Long id;
	
	@NotBlank(message="Invalid")
	private String name;
    
	@Min(value=1, message="Min. Price Re.1")
	@Max(value=200, message="Max. Price Rs.200")
	private double unitPrice;
	
	@Min(value=1, message="Min. Quantity 1 kg")
	@Max(value=100, message="Max. Quantity 100 kgs")
	private int availableQuantity;
	
	@Min(value=1, message="Invalid")
	@Max(value=1000, message="Invalid")
	private Long sellerId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	public Long getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
}
