package com.vmart.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Cart;
import com.vmart.model.Vegetable;
import com.vmart.model.dto.CartGetDto;
import com.vmart.model.dto.CartSetDto;
import com.vmart.service.CartService;
import com.vmart.service.VegetableService;
import com.vmart.utils.CartUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CartController {

	@Autowired
	private CartService service;

	@Autowired
	private VegetableService vegetableService;

	@PostMapping("/add-to-cart")
	public ResponseEntity<String> addToCart(@Valid @RequestBody CartSetDto cartItemDto) {
		try {
			service.addToCart(validateCart(cartItemDto));
			return ResponseEntity.ok("Added to cart successfully");
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping("/get-cart-items")
	public ResponseEntity<?> getCartItems(@RequestParam Long userId) {
		try {
			List<CartGetDto> cartItems = service.getCartItems(userId).stream().map(CartUtils::toDto)
					.collect(Collectors.toList());

			// TODO calculate total amount

			return ResponseEntity.ok(cartItems);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PutMapping("/update-cart-item")
	public ResponseEntity<String> updateCartItem(@Valid @RequestBody CartSetDto cartItemDto) {
		try {
			service.updateCartItem(validateCart(cartItemDto));
			return ResponseEntity.ok("Cart updated successfully");
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@DeleteMapping("/remove-from-cart")
	public ResponseEntity<String> removeFromCart(@RequestParam Long id) {
		try {
			service.removeFromCart(id);
			return ResponseEntity.ok("Removed from cart successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@DeleteMapping("/empty-cart")
	public ResponseEntity<String> emptyCart(@RequestParam Long userId) {
		try {
			service.emptyCart(userId);
			return ResponseEntity.ok("Cart emptied successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	// common validate method for add and update
	private Cart validateCart(CartSetDto cartItemDto) throws Exception {
		// TODO increase quantity if item already exists in cart

		Vegetable vegetable = vegetableService.getVegetableById(cartItemDto.getVegetableId());
		if (cartItemDto.getQuantity() > vegetable.getQuantity()) {
			throw new Exception("Looks like you want more than available quantity of " + vegetable.getName() + " from "
					+ vegetable.getUser().getName() + ". Please contact store manager.");
		}
		return CartUtils.toEntity(cartItemDto);
	}
}
