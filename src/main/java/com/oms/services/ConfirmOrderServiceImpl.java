package com.oms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.pojo.ConfirmOrder;
import com.oms.repositories.ConfirmOrderRepository;

@Service
public class ConfirmOrderServiceImpl implements ConfirmOrderService {
	
	@Autowired
	ConfirmOrderRepository orderRepository;

	@Override
	public List<ConfirmOrder> fetchOrder() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(String id) {
		orderRepository.delete(id);

	}

	@Override
	public void createOrder(ConfirmOrder order) {
	  orderRepository.save(order);

	}

	@Override
	public void updateOrder(ConfirmOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ConfirmOrder> fetchOrderByEmail(String email) {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

}
