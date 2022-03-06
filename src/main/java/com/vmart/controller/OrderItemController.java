package com.vmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmart.model.dto.OrderItemGetDto;
import com.vmart.service.OrderItemService;
import com.vmart.utils.OrderItemUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderItemController {

	@Autowired
	private OrderItemService service;

	@GetMapping("/get-order-items")
	public ResponseEntity<?> getOrderItems(@RequestParam Long orderId) {
		try {
			List<OrderItemGetDto> orderItems = OrderItemUtils.toDto(service.getOrderItems(orderId));
			return ResponseEntity.ok(orderItems);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body("Invalid request");
		}
	}

}
