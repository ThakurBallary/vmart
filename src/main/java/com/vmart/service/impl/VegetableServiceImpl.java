package com.vmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.Vegetable;
import com.vmart.repository.VegetableRepository;
import com.vmart.service.VegetableService;

@Service
@Transactional
public class VegetableServiceImpl implements VegetableService {

	@Autowired
	private VegetableRepository repository;

	@Override
	public void createVegetable(Vegetable vegetable) {
		repository.save(vegetable);
	}

	@Override
	public List<Vegetable> getVegetables() {
		return repository.findAll();
	}

	@Override
	public void updateVegetable(Vegetable vegetable) throws ResourceNotFoundException {
		Vegetable existingVegetable = repository.findById(vegetable.getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Could not update. Vegetable not found with id: " + vegetable.getId()));
		existingVegetable.setName(vegetable.getName());
		existingVegetable.setPrice(vegetable.getPrice());
		existingVegetable.setQuantity(vegetable.getQuantity());
		repository.save(existingVegetable);
	}

	@Override
	public void deleteVegetable(Long id) throws ResourceNotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Could not delete. Vegetable not found with id: " + id);
		}
	}

	@Override
	public Vegetable getVegetableById(Long id) throws ResourceNotFoundException {
		Vegetable vegetable = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vegetable not found with id: " + id));
		return vegetable;
	}

	@Override
	public List<Vegetable> getVegetablesById(List<Long> ids) throws Exception {
		return repository.findAllById(ids);
	}

	@Override
	public void updateVegetables(List<Vegetable> vegetables) throws Exception {
		repository.saveAll(vegetables);
	}

}
