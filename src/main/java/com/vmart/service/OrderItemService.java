package com.vmart.service;

import java.util.List;

import com.vmart.model.OrderItem;

public interface OrderItemService {
	public List<OrderItem> getOrderItems(Long orderId);
}
