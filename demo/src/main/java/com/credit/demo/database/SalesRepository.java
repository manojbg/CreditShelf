package com.credit.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.demo.model.Sales;

@Repository
public interface SalesRepository
extends JpaRepository<Sales, Long> {
	 
}
