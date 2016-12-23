package com.oms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oms.pojo.CustomerOrder;
import com.oms.services.CustomerOrderService;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerOrderController {

	@Autowired
	CustomerOrderService orderService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<CustomerOrder> createItem(@RequestBody CustomerOrder order) {
		orderService.createOrder(order);

		return new ResponseEntity<CustomerOrder>(order, HttpStatus.OK);
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<CustomerOrder> getOrder() {
		List<CustomerOrder> ordersLIst = orderService.fetchOrder();

		return ordersLIst;
	}

	@RequestMapping(value = "/fetchByEmail", method = RequestMethod.GET)
	@ResponseBody
	public List<CustomerOrder> getOrderByEmail(@RequestParam String email) {
		List<CustomerOrder> ordersLIst = orderService.fetchOrderByEmail(email);

		return ordersLIst;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOrder(@RequestParam String id) {
		orderService.deleteOrder(id);
		return "SUCCESS";
	}

}
