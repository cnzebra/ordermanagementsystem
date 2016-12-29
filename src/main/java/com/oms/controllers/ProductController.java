package com.oms.controllers;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.oms.pojo.Product;
import com.oms.pojo.User;
import com.oms.services.GoogleSignInServiceImpl;
import com.oms.services.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	
	ProductService prodService;

	@Autowired
	GoogleSignInServiceImpl signInService;

	@Autowired
	User user;
	
	
	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProduct(){
		System.out.println("Inside getProduct.........");
		List<Product> prod=prodService.fetchProd();
		return prod;
	}
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public void insertProduct(@RequestBody Product product){
		
		prodService.createProd(product);
		
		
	}
	
	@GetMapping()
	@RequestMapping("/getUser")
	public User getUser() {
		return signInService.user;
	}
	
}
