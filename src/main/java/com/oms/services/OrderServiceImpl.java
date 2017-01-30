package com.oms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.pojo.Order;
import com.oms.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;


	@Override
	public List<Order> fetchOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Integer orderId) {
		orderRepository.delete(orderId);

	}

	@Override
	public void createOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public Order fetchOrderById(Integer orderId) {
		return orderRepository.findOne(orderId);
	}

	@Override
	public void updateStatusReject(Integer orderId) {
		orderRepository.findOne(orderId);
	}

	@Override
	public void updateProcessedorder(Integer orderId) {
		orderRepository.findOne(orderId);
	}
}
