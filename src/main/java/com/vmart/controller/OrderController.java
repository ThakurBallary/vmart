package com.vmart.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmart.model.Cart;
import com.vmart.model.Order;
import com.vmart.model.Vegetable;
import com.vmart.model.dto.OrderGetDto;
import com.vmart.model.dto.OrderSetDto;
import com.vmart.service.CartService;
import com.vmart.service.OrderService;
import com.vmart.service.VegetableService;
import com.vmart.utils.OrderUtils;
import com.vmart.utils.VegetableUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {

	@Autowired
	private OrderService service;

	@Autowired
	private CartService cartService;

	@Autowired
	private VegetableService vegetableService;

	@PostMapping("/create-order")
	public ResponseEntity<String> createOrder(@Valid @RequestBody OrderSetDto orderDto) {
		try {
			List<Vegetable> vegetables = new ArrayList<Vegetable>();
			if (orderDto.isFromCart()) {
				List<Cart> cartItems = cartService.getCartItems(orderDto.getBuyerId());
				vegetables = cartItems.stream().map((e) -> e.getVegetable()).collect(Collectors.toList());
				orderDto.setOrderItems(OrderUtils.convert(cartItems));
			} else {
				List<Long> vegetableIds = orderDto.getOrderItems().stream().map((e) -> e.getVegetableId())
						.collect(Collectors.toList());
				vegetables = getVegetablesById(vegetableIds);
			}

			Order order = OrderUtils.toEntity(orderDto, vegetables);
			if (order.getOrderItems().isEmpty()) {
				throw new Exception("Your basket is empty. Add vegetable before you place the order.");
			}

			service.createOrder(order);
			if (!orderDto.isPaid()) {
				throw new Exception("Payment Failed.");
			};
			
			vegetableService.updateVegetables(VegetableUtils.syncAvailableQuantity(orderDto, vegetables));
			
			if (orderDto.isFromCart()) {
				cartService.emptyCart(orderDto.getBuyerId());
			}
			
			return ResponseEntity.ok("Order created successfully");
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping("/get-orders")
	public ResponseEntity<?> getOrders(@RequestParam Long userId) {
		try {
			List<OrderGetDto> orders = service.getOrders(userId).stream().map(OrderUtils::toDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(orders);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	private List<Vegetable> getVegetablesById(List<Long> vegetableIds) throws Exception {
		return vegetableService.getVegetablesById(vegetableIds);
	}

}
