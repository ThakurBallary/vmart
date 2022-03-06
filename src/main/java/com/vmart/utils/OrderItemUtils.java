package com.vmart.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.OrderItem;
import com.vmart.model.dto.OrderItemGetDto;

public class OrderItemUtils {

	public static List<OrderItemGetDto> toDto(List<OrderItem> orderItems) throws ResourceNotFoundException {
		return orderItems.stream().map((orderItem) -> {
			OrderItemGetDto dto = new OrderItemGetDto();
			dto.setId(orderItem.getId());
			dto.setQuantity(orderItem.getQuantity());
			dto.setSeller(orderItem.getSeller());
			dto.setUnitPrice(orderItem.getPrice());
			dto.setVegetable(orderItem.getVegetable());
			return dto;
		}).collect(Collectors.toList());
	}

}
