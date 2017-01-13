package com.oms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oms.pojo.Order;
import com.oms.pojo.User;
import com.oms.services.GoogleSignInServiceImpl;
import com.oms.services.OrderService;


public class OrderControllerTest {

	@Autowired
	OrderService orderService;

	@Autowired
	GoogleSignInServiceImpl signInService;

	@Autowired
	User user;

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<Order> getOrders() {
		List<Order> order = orderService.fetchOrders();
		return order;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertOrder(@RequestBody Order order) {
		orderService.createOrder(order);

	}

	@RequestMapping(value = "/getOrder/{orderId}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable("orderId") Integer orderId) {
		Order order = orderService.fetchOrderById(orderId);
		return order;
	}

	@RequestMapping(value = "/update/{orderId}", method = RequestMethod.PUT)
	public void updateStatusRejected(@PathVariable("orderId") Integer orderId) {
		Order order = orderService.fetchOrderById(orderId);
		order.setStatus("rejected");
		orderService.createOrder(order);
	}

	@RequestMapping(value = "/updateProcessOrder/{orderId}", method = RequestMethod.PUT)
	public void updateOrderProcess(@PathVariable("orderId") Integer orderId) {
		Order order = orderService.fetchOrderById(orderId);
		order.setStatus("processed");
		orderService.createOrder(order);
	}

	@GetMapping()
	@RequestMapping("/getUser")
	public User getUser() {
		return signInService.user;
	}
}
