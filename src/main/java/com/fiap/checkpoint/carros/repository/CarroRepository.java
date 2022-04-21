package com.fiap.checkpoint.carros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.checkpoint.carros.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {
	
}
