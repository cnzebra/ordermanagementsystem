package com.oms.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oms.pojo.Product;
import com.oms.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<Product> fetchProd() {
		// TODO Auto-generated method stub
		System.out.println("Inside fetchProd...");
		List<Product>products=prodRepo.findAll();
		System.out.println("products...."+products.toString());
		return products;
	}

	@Override
	public void deleteProd(String id) {
		// TODO Auto-generated method stub
      
	}

	@Override
	public void createProd(Product prod) {
		// TODO Auto-generated method stub
		System.out.println("Inside createProd.......");
		prodRepo.save(prod);

	}

	@Override
	public void updateProd(Product prod) {
		// TODO Auto-generated method stub

	}

}
