package com.vmart.service;

import java.util.List;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Cart;

public interface CartService {
	void addToCart(Cart cartItem);
	List<Cart> getCartItems(Long userId);
	void updateCartItem(Cart cartItem) throws Exception;
	void removeFromCart(Long id) throws ResourceNotFoundException;
	void emptyCart(Long userId) throws ResourceNotFoundException;
}
