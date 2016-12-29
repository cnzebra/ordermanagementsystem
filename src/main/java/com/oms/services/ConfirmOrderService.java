package com.oms.services;

import java.util.List;


import com.oms.pojo.ConfirmOrder;

public interface ConfirmOrderService {
	List<ConfirmOrder> fetchOrder();
	void deleteOrder(String id);
	void createOrder(ConfirmOrder order);
	void updateOrder(ConfirmOrder order);
	List<ConfirmOrder> fetchOrderByEmail(String email);

}
