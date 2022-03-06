package com.vmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmart.model.OrderItem;
import com.vmart.repository.OrderItemRepository;
import com.vmart.service.OrderItemService;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository repository;

	@Override
	public List<OrderItem> getOrderItems(Long orderId) {
		return repository.findByOrderId(orderId);
	}

}
