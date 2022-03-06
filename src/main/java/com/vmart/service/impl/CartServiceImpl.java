package com.vmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Cart;
import com.vmart.repository.CartRepository;
import com.vmart.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository repository;

	@Override
	public void addToCart(Cart cartItem) {
		repository.save(cartItem);
	}

	@Override
	public List<Cart> getCartItems(Long userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public void updateCartItem(Cart cartItem) throws Exception {
		Cart existingCartItem = repository.findById(cartItem.getId()).orElseThrow(() -> new Exception(
				"Could not update. Item not found with id: " + cartItem.getId() + " in cart."));
		existingCartItem.setQuantity(cartItem.getQuantity());
		repository.save(existingCartItem);
	}

	@Override
	public void removeFromCart(Long id) throws ResourceNotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Could not remove. Item not found with id: " + id + " in cart.");
		}
	}

	@Override
	public void emptyCart(Long userId) throws ResourceNotFoundException {
		if (repository.existsByUserId(userId)) {
			repository.deleteByUserId(userId);
		} else {
			throw new ResourceNotFoundException("No cart found");
		}
	}

}
