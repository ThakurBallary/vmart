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
import com.vmart.model.Vegetable;
import com.vmart.model.dto.VegetableGetDto;
import com.vmart.model.dto.VegetableSetDto;
import com.vmart.service.VegetableService;
import com.vmart.utils.VegetableUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VegetableController {

	@Autowired
	private VegetableService service;

	@PostMapping("/add-vegetable")
	public ResponseEntity<String> createVegetable(@Valid @RequestBody VegetableSetDto vegetableSetDto) {
		try {
			Vegetable vegetable = VegetableUtils.toEntity(vegetableSetDto);
			service.createVegetable(vegetable);
			return ResponseEntity.ok("Vegetable created successfully");
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping("/get-vegetables")
	public ResponseEntity<?> getVegetables() {
		try {
			List<VegetableGetDto> vegetables = service.getVegetables().stream().map(VegetableUtils::toDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(vegetables);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PutMapping("/update-vegetable")
	public ResponseEntity<String> updateVegetable(@Valid @RequestBody VegetableSetDto vegetableSetDto)
			throws ResourceNotFoundException {
		try {
			Vegetable vegetable = VegetableUtils.toEntity(vegetableSetDto);
			service.updateVegetable(vegetable);
			return ResponseEntity.ok("Vegetable updated successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete-vegetable")
	public ResponseEntity<String> deleteVegetable(@RequestParam Long id) throws ResourceNotFoundException {
		try {
			service.deleteVegetable(id);
			return ResponseEntity.ok("Vegetable deleted successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
