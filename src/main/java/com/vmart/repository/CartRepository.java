package com.vmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	List<Cart> findByUserId(Long id);
	boolean existsByUserId(Long id);
	void deleteByUserId(Long id);
	
}
