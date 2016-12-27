package com.oms.services;

import java.util.List;

import com.oms.pojo.Product;

public interface ProductService {
   
	List<Product> fetchProd();
	void deleteProd(String id);
	void createProd(Product prod);
	void updateProd(Product prod);
	
}
