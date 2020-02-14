package com.credit.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.demo.database.ProductRespository;
import com.credit.demo.model.Product;

@Service
public class ProductService {
	
	@Autowired
    ProductRespository repository;
	
	public void createOrUpdateProduct(Product entity) throws Exception
    {
            repository.save(entity);
    }

}
