package com.credit.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.demo.model.Order;


@Repository
public interface OrderRepository 
extends JpaRepository<Order, Long> {
	 
}
