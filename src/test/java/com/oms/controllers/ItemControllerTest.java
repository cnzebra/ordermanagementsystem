package com.oms.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.oms.pojo.Item;
import com.oms.services.ItemService;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@InjectMocks
	private ItemController itemController = new ItemController();

	@Mock
	ItemService itemService;

	private MockMvc mockMvc;

	List<Item> items = new ArrayList<>();
	Item item = new Item();

	@Before
	public void setup() throws Exception {

		item.setItemId(100);
		item.setItemName("GAP");
		item.setPrice(1000);
		item.setQuantity(10);
		item.setCreatedDate(new Date());
		items.add(item);

		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		viewResolver.setPrefix("/static/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(itemController).setViewResolvers(viewResolver).build();

	}

	@Test
	public final void listAllItemsWithNoContentTest() {

		ResponseEntity<List<Item>> listItems = itemController.listAllItems();
		Assert.assertEquals(204, listItems.getStatusCodeValue());
	}

	@Test
	public final void listAllItemsWithContentTest() throws Exception {

		this.mockMvc.perform(get("/item/itemList")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public final void getItemTest() throws Exception {

		this.mockMvc.perform(get("/item/get/").param("id", "2")).andExpect(status().is4xxClientError());
	}

}
