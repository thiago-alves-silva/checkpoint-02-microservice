package com.fiap.checkpoint.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.checkpoint.carros.model.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
	
}
