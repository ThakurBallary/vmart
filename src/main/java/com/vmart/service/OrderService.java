package com.vmart.service;

import java.util.List;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Order;

public interface OrderService {
	void createOrder(Order order);
	List<Order> getOrders(Long userId);
	void updateOrderStatus(Order order) throws ResourceNotFoundException;
	void deleteOrder(Long id) throws ResourceNotFoundException;
}
