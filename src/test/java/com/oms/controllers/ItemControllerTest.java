package com.oms.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.oms.pojo.Item;
import com.oms.services.ItemService;
import com.oms.util.MockTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@InjectMocks
	private ItemController itemController = new ItemController();

	@Mock
	ItemService itemService;

	private MockMvc mockMvc;

	@Resource
	private List<HandlerExceptionResolver> webApplicationContext;

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

		this.mockMvc = MockMvcBuilders.standaloneSetup(itemController)
				.setHandlerExceptionResolvers(webApplicationContext).build();

	}

	@Test
	public void listAllItemsTest() throws Exception {
		when((itemService).findAllItems()).thenReturn(items);
		mockMvc.perform(get("/item/itemList").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Item()))).andExpect(status().isOk());
	}

	@Test
	public void listAllItemsWithEmptyTest() throws Exception {
		mockMvc.perform(get("/item/itemList").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Item()))).andExpect(status().isNoContent());
	}
	
	@Test
	public void getItemTest() throws Exception {
		Integer id = 1;
		when((itemService).findItemById(id)).thenReturn(item);
		mockMvc.perform(get("/item/get/1").contentType(MediaType.APPLICATION_JSON).param("id", "1"))
				.andExpect(status().isOk());
	}

	@Test
	public void createItemTest() throws Exception {
		doNothing().when(itemService).saveItem(item);
		mockMvc.perform(post("/item/create").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Item()))).andExpect(status().isCreated());
	}
	
	@Test
	public void updateItemTest() throws Exception {

		doNothing().when(itemService).updateItem(item);
		mockMvc.perform(put("/item/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(MockTestUtil.convertToJsonFormat(new Item()))).andExpect(status().isOk());
	}

	@Test
	public void deleteItemTest() throws Exception {

		Integer id = 1;
		when((itemService).findItemById(id)).thenReturn(item);
		doNothing().when(itemService).deleteItemById(id);
		mockMvc.perform(delete("/item/delete/1").param("id", "1")).andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteItemWithNullTest() throws Exception {

		Integer id = 1;
		when((itemService).findItemById(id)).thenReturn(null);
		doNothing().when(itemService).deleteItemById(id);
		mockMvc.perform(delete("/item/delete/1").param("id", "1")).andExpect(status().isNotFound());
	}


	@Test
	public void itemsModificationsTest() throws Exception {

		Integer id = 1;
		when((itemService).findItemById(id)).thenReturn(item);
		doNothing().when(itemService).itemModification(item);
		mockMvc.perform(put("/item/isModify/1/10").param("id", "1").param("quantity", "10")).andExpect(status().isOk());
	}
	
	@Test
	public void isItemExistTest() throws Exception {
		Integer id = 1;
		when((itemService).findItemById(id)).thenReturn(item);
		mockMvc.perform(get("/item/isExist/1").contentType(MediaType.APPLICATION_JSON).param("id", "1"))
				.andExpect(status().isFound());
	}

	@Test
	public void isItemExistWithNullTest() throws Exception {
		Integer id = 1;
		when((itemService).findItemById(id)).thenReturn(null);
		mockMvc.perform(get("/item/isExist/1").contentType(MediaType.APPLICATION_JSON).param("id", "1"))
				.andExpect(status().isNotFound());
	}
	
	
	
}
