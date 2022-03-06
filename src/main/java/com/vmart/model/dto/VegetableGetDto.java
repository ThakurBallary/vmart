package com.vmart.model.dto;

public class VegetableGetDto {
	
	private Long id;
	private String name;
    private double unitPrice;
	private int availableQuantity;
	private String seller;
	
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
	
	public String getSeller() {
		return seller;
	}
	
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
}
