package com.vmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmart.model.Vegetable;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long>{

}
