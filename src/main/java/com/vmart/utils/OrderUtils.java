package com.vmart.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.vmart.model.Cart;
import com.vmart.model.Order;
import com.vmart.model.OrderItem;
import com.vmart.model.User;
import com.vmart.model.Vegetable;
import com.vmart.model.dto.OrderGetDto;
import com.vmart.model.dto.OrderItemSetDto;
import com.vmart.model.dto.OrderSetDto;

public class OrderUtils {

	public static OrderGetDto toDto(Order order) {
		OrderGetDto dto = new OrderGetDto();
		dto.setId(order.getId());
		dto.setAmount(order.getAmount());
		dto.setDate(order.getDate());
		dto.setPaid(order.isPaid());
		return dto;
	}

	public static Order toEntity(OrderSetDto dto, List<Vegetable> vegetables) throws Exception {
		Order order = new Order();
		order.setPaid(dto.isPaid());

		User buyer = new User();
		buyer.setId(dto.getBuyerId());
		order.setUser(buyer);

		double amount = 0;
		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		for (OrderItemSetDto orderItemDto : dto.getOrderItems()) {
			Vegetable vegetable = vegetables.stream().filter(v -> v.getId() == orderItemDto.getVegetableId())
					.findFirst()
					.orElseThrow(() -> new Exception("Vegetable not found with id: " + orderItemDto.getVegetableId()));
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(vegetable.getPrice());
			if (orderItemDto.getQuantity() < 1) {
				throw new Exception("You forgot to add quantity for " + vegetable.getName());
			}
			if (orderItemDto.getQuantity() > vegetable.getQuantity()) {
				throw new Exception("Looks like you want more than available quantity of " + vegetable.getName()
						+ " from " + vegetable.getUser().getName() + ". Please contact store manager.");
			}
			orderItem.setQuantity(orderItemDto.getQuantity());
			orderItem.setSeller(vegetable.getUser().getName());
			orderItem.setVegetable(vegetable.getName());
			orderItems.add(orderItem);

			amount += (vegetable.getPrice() * orderItem.getQuantity());
		}

		order.setAmount(amount);
		order.setOrderItems(orderItems);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss a");
		order.setDate(LocalDateTime.now().format(formatter));

		return order;
	}
	
	public static List<OrderItemSetDto> convert(List<Cart> cartItems) {
		List<OrderItemSetDto> orderItems = new ArrayList<OrderItemSetDto>();
		for (Cart cartItem : cartItems) {
			OrderItemSetDto dto = new OrderItemSetDto();
			dto.setQuantity(cartItem.getQuantity());
			dto.setVegetableId(cartItem.getVegetable().getId());
			orderItems.add(dto);
		}
		return orderItems;
	}
	
}
