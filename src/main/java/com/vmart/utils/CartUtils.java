package com.vmart.utils;

import com.vmart.model.Cart;
import com.vmart.model.User;
import com.vmart.model.Vegetable;
import com.vmart.model.dto.CartGetDto;
import com.vmart.model.dto.CartSetDto;
import com.vmart.model.dto.VegetableGetDto;

public class CartUtils {
	
	public static Cart toEntity(CartSetDto dto) {
		Cart cartItem = new Cart();
		cartItem.setId(dto.getId());
		cartItem.setQuantity(dto.getQuantity());
		
		User buyer = new User();
		buyer.setId(dto.getBuyerId());
		cartItem.setUser(buyer);
		
		Vegetable vegetable = new Vegetable();
		vegetable.setId(dto.getVegetableId());
		cartItem.setVegetable(vegetable);
		
		return cartItem;
	}
	
	public static CartGetDto toDto(Cart cartItem) {
		CartGetDto dto = new CartGetDto();
		dto.setId(cartItem.getId());
		dto.setQuantity(cartItem.getQuantity());
		
		VegetableGetDto vegetableGetDto = VegetableUtils.toDto(cartItem.getVegetable());
		dto.setVegetable(vegetableGetDto);
		return dto;
	}

}
