package com.oms.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oms.mail.SmtpMailSender;
import com.oms.pojo.Order;
import com.oms.pojo.User;
import com.oms.services.GoogleSignInServiceImpl;
import com.oms.services.OrderService;

@RefreshScope
@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	Logger log=Logger.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;
	
	
	@Autowired
	SmtpMailSender mailSender;

	@Autowired
	GoogleSignInServiceImpl signInService;

	@Autowired
	User user;

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<Order> getOrders() {
		return orderService.fetchOrders();
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertOrder(@RequestBody Order order) {
		orderService.createOrder(order);

	}

	@RequestMapping(value = "/getOrder/{orderId}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable("orderId") Integer orderId) {
		return orderService.fetchOrderById(orderId);
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
		try {
			mailSender.send("msambaiah@nisum.com", "ItemConfirmation",
					"Thank You for Placing order,you Order details are as follows" + "orderId= " + order.getOrderId()
							+ " " + "orderAmount=" + order.getOrderAmount());
		} catch (Exception e) {
			log.error(e);
		}
	}

	@GetMapping()
	@RequestMapping("/getUser")
	public User getUser() {
		return signInService.user;
	}
}
