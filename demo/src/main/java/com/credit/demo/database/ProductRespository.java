package com.credit.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.demo.model.Product;

@Repository
public interface ProductRespository
extends JpaRepository<Product, Long> {
	 
}
