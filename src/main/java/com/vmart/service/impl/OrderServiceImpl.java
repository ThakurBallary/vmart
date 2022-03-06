package com.vmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Order;
import com.vmart.repository.OrderRepository;
import com.vmart.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Override
	public void createOrder(Order order) {
		repository.save(order);
	}

	@Override
	public List<Order> getOrders(Long userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public void updateOrderStatus(Order order) throws ResourceNotFoundException {
		Order existingOrder = repository.findById(order.getId()).orElseThrow(() -> new ResourceNotFoundException(
				"Order not found."));
		existingOrder.setPaid(order.isPaid());
		repository.save(existingOrder);
	}

	@Override
	public void deleteOrder(Long id) throws ResourceNotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Order not found.");
		}
	}

}
