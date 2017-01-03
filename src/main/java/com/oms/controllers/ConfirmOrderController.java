package com.oms.controllers;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oms.mail.SmtpMailSender;
import com.oms.pojo.ConfirmOrder;
import com.oms.services.ConfirmOrderService;

@RestController
@RequestMapping(value = "/Customer")
public class ConfirmOrderController {

	@Autowired
	ConfirmOrderService orderService;
	@Autowired
	SmtpMailSender mailSender;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ConfirmOrder> createItem(@RequestBody ConfirmOrder order) throws MessagingException {
		orderService.createOrder(order);
		
		 mailSender.send(order.getEmail(), "Item Confirmation", "your order is confirmed with orderId=   "+order.getId()
		 +"    orderDate is    "+order.getOrder_date()+"    deliverydate is   "+order.getDelivery_date()+ "   delivaryAddress   "+order.getDelivery_address());

		return new ResponseEntity<ConfirmOrder>(order, HttpStatus.OK);
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseBody
	public List<ConfirmOrder> getOrder() {
		List<ConfirmOrder> ordersLIst = orderService.fetchOrder();

		return ordersLIst;
	}

	@RequestMapping(value = "/fetchByEmail", method = RequestMethod.GET)
	@ResponseBody
	public List<ConfirmOrder> getOrderByEmail(@RequestParam String email) {
		List<ConfirmOrder> ordersLIst = orderService.fetchOrderByEmail(email);

		return ordersLIst;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOrder(@RequestParam String id) {
		orderService.deleteOrder(id);
		return "SUCCESS";
	}

}
