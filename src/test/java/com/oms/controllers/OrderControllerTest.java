package com.oms.controllers;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.oms.pojo.Order;
import com.oms.services.OrderService;


public class OrderControllerTest {

	@Mock
	 private OrderService orderService;
	
	@InjectMocks
	private OrderController orderController=new OrderController();
	
	public final void  getOrdersTest() {
		
		List<Order> orders = orderController.getOrders();
		
		
	}
	
	
	public List<Order> getOrders() {
		List<Order> order = orderService.fetchOrders();
		return order;
	}
	
	
}
