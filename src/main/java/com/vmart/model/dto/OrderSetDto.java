package com.vmart.model.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OrderSetDto {
	
	@Min(value=1, message="Invalid")
	@Max(value=1000, message="Invalid")
	private Long buyerId;
	
	private boolean fromCart;
	
	private List<OrderItemSetDto> orderItems;
	
	private boolean paid;

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public boolean isFromCart() {
		return fromCart;
	}

	public void setFromCart(boolean fromCart) {
		this.fromCart = fromCart;
	}

	public List<OrderItemSetDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemSetDto> orderItems) {
		this.orderItems = orderItems;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "OrderSetDto [buyerId=" + buyerId + ", fromCart=" + fromCart + ", orderItems=" + orderItems
				+ ", paid=" + paid + "]";
	}

}
