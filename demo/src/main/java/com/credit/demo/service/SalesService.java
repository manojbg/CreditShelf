package com.credit.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.demo.database.SalesRepository;
import com.credit.demo.model.Sales;

@Service
public class SalesService {

	@Autowired
    SalesRepository repository;
	
	public void createOrUpdateProduct(Sales entity) throws Exception
    {
            repository.save(entity);
    }
}
