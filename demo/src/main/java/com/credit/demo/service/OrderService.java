package com.credit.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.demo.database.OrderRepository;
import com.credit.demo.model.Order;

@Service
public class OrderService {

	@Autowired
    OrderRepository repository;
	
	public void createOrUpdateProduct(Order entity) throws Exception
    {
            repository.save(entity);
    }
}
