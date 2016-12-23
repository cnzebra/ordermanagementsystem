package com.oms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.pojo.CustomerOrder;
import com.oms.repositories.CustomerOrderRepository;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
	
	@Autowired
	CustomerOrderRepository orderRepository;

	@Override
	public List<CustomerOrder> fetchOrder() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(String id) {
		orderRepository.delete(id);

	}

	@Override
	public void createOrder(CustomerOrder order) {
	  orderRepository.save(order);

	}

	@Override
	public void updateOrder(CustomerOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CustomerOrder> fetchOrderByEmail(String email) {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

}
