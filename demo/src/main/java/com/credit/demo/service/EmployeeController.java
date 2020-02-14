package com.credit.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.credit.demo.model.EmployeeEntity;
import com.credit.demo.model.Order;
import com.credit.demo.model.Product;
import com.credit.demo.model.Sales;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService service;
    @Autowired
    ProductService prodservice;
    @Autowired
    SalesService saldservice;
    @Autowired
    OrderService orderdservice;
 
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();
 
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws Exception {
        EmployeeEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody EmployeeEntity employee)
                                                    throws Exception {
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws Exception {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
    
    @RequestMapping(value="/uploadfiles",method=RequestMethod.POST)
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee()
                                                    throws Exception {
    	System.out.println("Inside file upload controller");
		Product product = new Product();
		InputStream Acmest = product.getClass().getResourceAsStream("/files/Acme_corporation.csv"); 	
		InputStream capsulst = product.getClass().getResourceAsStream("/files/Capsule_Corporation.csv"); 
		InputStream oilst = product.getClass().getResourceAsStream("/files/Ollivanders_Wand_Shop.csv"); 
		InputStream sales = product.getClass().getResourceAsStream("/files/Sales.csv"); 
	    BufferedReader br = null;
	    String line = "";
	    String cvsSplitBy = ",";

	    try {
	        br = new BufferedReader(new InputStreamReader(Acmest));
	        while ((line = br.readLine()) != null) {
	            // use comma as separato1
	            String[] values = line.split(cvsSplitBy);
	            if(!values[0].equalsIgnoreCase("Description")){
	            	Product prod = new Product();
	            	prod.setCompany("Acme_corporation");
	            	prod.setCompany_prodid(values[4]);
	            	prod.setCost(values[1]+values[2]);
	            	prod.setCurrency(values[3]);
	            	System.out.println(values[3]);
	            	prod.setName(values[0]);
	            	prodservice.createOrUpdateProduct(prod);
	            }
	        }
	        br = new BufferedReader(new InputStreamReader(capsulst));
	        while ((line = br.readLine()) != null) {
	            // use comma as separato1
	            String[] values = line.split(cvsSplitBy);
	            if(!values[0].equalsIgnoreCase("id")){
	            	Product prod = new Product();
	            	prod.setCompany("Capsule_Corporation");
	            	prod.setCompany_prodid(values[0]);
	            	prod.setCost(values[2]);
	            	prod.setCurrency(values[3]);
	            	System.out.println(values[3]);
	            	prod.setName(values[1]);
	            	prodservice.createOrUpdateProduct(prod);
	            }
	        }
	        
	        br = new BufferedReader(new InputStreamReader(oilst));
	        while ((line = br.readLine()) != null) {
	            // use comma as separato1
	            String[] values = line.split(cvsSplitBy);
	            if(!values[0].equalsIgnoreCase("Product Id")){
	            	Product prod = new Product();
	            	prod.setCompany("Ollivanders_Wand_Shop");
	            	prod.setCompany_prodid(values[0]);
	            	prod.setCost(values[2]);
	            	prod.setCurrency(values[3]);
	            	System.out.println(values[3]);
	            	prod.setName(values[1]);
	            	prodservice.createOrUpdateProduct(prod);
	            }
	        }
	        
	        br = new BufferedReader(new InputStreamReader(sales));
	        String date="";
	        String company = "";
	        int ornumber = 0;
	        while ((line = br.readLine()) != null) {
	            // use comma as separato1
	            String[] values = line.split(cvsSplitBy);
	            if(!values[0].equalsIgnoreCase("company")){
	            	Sales sale = new Sales();
	            	Order order = new Order();
	            	System.out.println("value-->"+values[0]+"hah");
	            	if(values[0].length()!=0){
	            		date=values[1];
		            	company=values[0];
		            	ornumber=Integer.parseInt(values[2]);
	            	}
	            	
	            	sale.setCompany(company);
	            	sale.setOrder_id(ornumber);
	            	sale.setQuality(Integer.parseInt(values[4]));
	            	sale.setPrice(values[5]);
	            	sale.setProduct_id(Integer.parseInt(values[3]));
	            	sale.setCurrency(values[6]);
	            	
	            	
	            	order.setDate(date);
	            	order.setCompany(company);
	            	order.setNumber(ornumber);
	            	
	            	saldservice.createOrUpdateProduct(sale);
	            	orderdservice.createOrUpdateProduct(order);
	            	
	            	//prodservice.createOrUpdateProduct(prod);
	            }
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
        return new ResponseEntity<EmployeeEntity>(new HttpHeaders(), HttpStatus.OK);
    }
 
}