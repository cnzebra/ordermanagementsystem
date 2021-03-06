package com.oms.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.oms.pojo.Item;
import com.oms.pojo.Order;
import com.oms.repositories.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	private static final Integer ORDERID = 123;
	private static final Date ORDERDATE = new Date();
	private static final Integer ORDERAMOUNT = 100;
	private static final String STATUS = "status";

	@InjectMocks
	private OrderServiceImpl orderService = new OrderServiceImpl();

	@Mock
	private OrderRepository orderRepository;

	Order order = new Order();
	List<Item> items = new ArrayList<>();
	List<Order> orders = new ArrayList<>();

	@Before
	public void setUp() throws Exception {

		order.setOrderId(ORDERID);
		order.setOrderAmount(ORDERAMOUNT);
		order.setOrderDate(ORDERDATE);
		order.setStatus(STATUS);
		order.setItem(items);

	}

	@Test
	public void fetchOrdersTest() {
		when(orderRepository.findAll()).thenReturn(orders);
		List<Order> expectedorders = orderService.fetchOrders();
		assertEquals(expectedorders, orders);
	}

	@Test
	public void fetchOrderByIdTest() {

		when(orderRepository.findOne(ORDERID)).thenReturn(order);
		Order expectedorder = orderService.fetchOrderById(ORDERID);
		assertEquals(expectedorder, order);
	}

	@Test
	public void createOrderTest() {

		when(orderRepository.save(order)).thenReturn(order);
		orderService.createOrder(order);
		verify(orderRepository, times(1)).save(order);

	}

	@Test
	public void deleteOrderTest() {
		doNothing().when(orderRepository).delete(ORDERID);
		orderService.deleteOrder(ORDERID);
		verify(orderRepository, times(1)).delete(ORDERID);
	}

	@Test
	public void updateStatusRejectTest() {

		when(orderRepository.findOne(ORDERID)).thenReturn(order);

		orderService.updateStatusReject(ORDERID);
		verify(orderRepository, times(1)).findOne(ORDERID);

	}

	@Test
	public void updateProcessedorderTest() {
		when(orderRepository.findOne(ORDERID)).thenReturn(order);
		orderService.updateProcessedorder(ORDERID);
		verify(orderRepository, times(1)).findOne(ORDERID);

	}

}
