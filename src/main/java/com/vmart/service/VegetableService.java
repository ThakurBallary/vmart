package com.vmart.service;

import java.util.List;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Vegetable;

public interface VegetableService {
	void createVegetable(Vegetable vegetable);
	List<Vegetable> getVegetables();
	void updateVegetable(Vegetable vegetable) throws ResourceNotFoundException;
	void updateVegetables(List<Vegetable> vegetables) throws Exception;
	void deleteVegetable(Long id) throws ResourceNotFoundException;
	Vegetable getVegetableById(Long id)  throws ResourceNotFoundException;
	List<Vegetable> getVegetablesById(List<Long> ids) throws Exception;
	
}
