package com.vmart.utils;

import java.util.List;
import java.util.Optional;

import com.vmart.model.User;
import com.vmart.model.Vegetable;
import com.vmart.model.dto.OrderItemSetDto;
import com.vmart.model.dto.OrderSetDto;
import com.vmart.model.dto.VegetableGetDto;
import com.vmart.model.dto.VegetableSetDto;

public class VegetableUtils {

	public static Vegetable toEntity(VegetableSetDto vegetableSetDto) {
		Vegetable vegetable = new Vegetable();
		vegetable.setId(vegetableSetDto.getId());
		vegetable.setName(vegetableSetDto.getName());
		vegetable.setPrice(vegetableSetDto.getUnitPrice());
		vegetable.setQuantity(vegetableSetDto.getAvailableQuantity());

		User seller = new User();
		seller.setId(vegetableSetDto.getSellerId());
		vegetable.setUser(seller);

		return vegetable;
	}

	public static VegetableGetDto toDto(Vegetable vegetable) {
		VegetableGetDto vegetableGetDto = new VegetableGetDto();
		vegetableGetDto.setId(vegetable.getId());
		vegetableGetDto.setName(vegetable.getName());
		vegetableGetDto.setUnitPrice(vegetable.getPrice());
		vegetableGetDto.setAvailableQuantity(vegetable.getQuantity());
		vegetableGetDto.setSeller(vegetable.getUser().getName());
		return vegetableGetDto;
	}

	public static List<Vegetable> syncAvailableQuantity(OrderSetDto dto, List<Vegetable> vegetables) {
		for (OrderItemSetDto orderItemDto : dto.getOrderItems()) {
			Optional<Vegetable> vegetable = vegetables.stream().filter(v -> v.getId() == orderItemDto.getVegetableId()).findFirst();
			if (vegetable.isPresent()) {
				vegetable.get().setQuantity(vegetable.get().getQuantity() - orderItemDto.getQuantity());
			}
		}
		return vegetables;
	}

}
