package com.oms.services;

import java.util.List;


import com.oms.pojo.CustomerOrder;

public interface CustomerOrderService {
	List<CustomerOrder> fetchOrder();
	void deleteOrder(String id);
	void createOrder(CustomerOrder order);
	void updateOrder(CustomerOrder order);
	List<CustomerOrder> fetchOrderByEmail(String email);

}
