package com.oms.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.oms.mail.SmtpMailSender;
import com.oms.pojo.Item;
import com.oms.pojo.Order;
import com.oms.pojo.User;
import com.oms.services.GoogleSignInServiceImpl;
import com.oms.services.OrderService;
import com.oms.util.MockTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	Logger log = Logger.getLogger(OrderControllerTest.class);

	@InjectMocks
	OrderController orderController = new OrderController();

	@Mock
	OrderService orderService;

	@Mock
	SmtpMailSender mailSender;

	@Mock
	GoogleSignInServiceImpl signInService;

	@Mock
	User user;

	List<Order> orders = new ArrayList<>();
	List<Item> items = new ArrayList<>();
	Order order = new Order();

	private MockMvc mockMvc;

	@Resource
	private List<HandlerExceptionResolver> webApplicationContext;

	@Before
	public void setup() throws Exception {

		order.setOrderId(1);
		order.setOrderAmount(100);
		order.setStatus("true");
		order.setOrderDate(new Date());
		order.setItem(items);
		orders.add(order);

		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController)
				.setHandlerExceptionResolvers(webApplicationContext).build();

	}

	@Test
	public void getOrdersTest() throws Exception {
		when((orderService).fetchOrders()).thenReturn(orders);
		mockMvc.perform(get("/order/fetch").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Order()))).andExpect(status().isOk());
	}

	@Test
	public void insertOrderTest() throws Exception {
		doNothing().when(orderService).createOrder(order);
		mockMvc.perform(post("/order/insert").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Order()))).andExpect(status().isOk());
	}

	@Test
	public void getOrderByIdTest() throws Exception {
		Integer orderId = 1;
		when((orderService).fetchOrderById(orderId)).thenReturn(order);
		mockMvc.perform(get("/order/getOrder/1").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Order()))).andExpect(status().isOk());
	}

	@Test
	public void updateStatusRejectedTest() throws Exception {
		Integer orderId = 1;
		when((orderService).fetchOrderById(orderId)).thenReturn(order);
		doNothing().when(orderService).createOrder(order);
		mockMvc.perform(put("/order/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Order()))).andExpect(status().isOk());
	}

	@Test
	public void updateOrderProcessTest() throws Exception {
		Integer orderId = 1;
		when((orderService).fetchOrderById(orderId)).thenReturn(order);
		doNothing().when(orderService).createOrder(order);
		doNothing().when(mailSender).send("abc@nisum.com", "ItemConfirmation", "Welcome");
		mockMvc.perform(put("/order/updateProcessOrder/1").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Order()))).andExpect(status().isOk());
	}
}
