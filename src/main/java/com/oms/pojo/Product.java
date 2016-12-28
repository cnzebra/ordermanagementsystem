package com.oms.pojo;

import org.springframework.data.annotation.Id;

public class Product {
	
	
	
	private String name;
	private int quantity;
	private String price;
	private String description;
	private String imagename;
	//private String product_name;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getProduct() {
		return name;
	}
	public void setProduct(String name) {
	
		this.name = name;
		System.out.print("product name....."+name);
	}
	

}
