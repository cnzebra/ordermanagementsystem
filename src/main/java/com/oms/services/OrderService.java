package com.oms.services;

import java.util.List;

import com.oms.pojo.Order;

public interface OrderService {
	List<Order> fetchOrders();

	void deleteOrder(Integer orderId);

	void createOrder(Order order);

	Order fetchOrderById(Integer orderId);

	void updateStatusReject(Integer orderId);

	void updateProcessedorder(Integer orderId);

}
