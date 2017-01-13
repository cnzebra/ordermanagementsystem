package com.oms.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oms.pojo.Item;
import com.oms.repositories.ItemRepository;

public class ItemServiceImplTest {

	private static final Integer ITEMID = 123;
	private static final String ITEMNAME = "Gap";
	private static final Integer QUANTITY = 100;
	private static final Integer PRICE = 10000;
	private static final Date CREATEDDATE = new Date();

	@InjectMocks
	private ItemServiceImpl itemService = new ItemServiceImpl();

	@Mock
	private ItemRepository itemRepository;

	Item item = new Item();
	List<Item> items = new ArrayList<>();

	@Before
	public void setUp() throws Exception {

		item.setItemId(ITEMID);
		item.setItemName(ITEMNAME);
		item.setQuantity(QUANTITY);
		item.setPrice(PRICE);
		item.setCreatedDate(CREATEDDATE);

		items.add(item);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public final void findItemByIdTest() {

		when(itemRepository.findOne(ITEMID)).thenReturn(item);
		Item expectedItem = itemService.findItemById(ITEMID);
		assertEquals(expectedItem, item);
	}

	@Test
	public final void findAllItemsTest() {

		when(itemRepository.findAll()).thenReturn(items);
		List<Item> items1 = itemService.findAllItems();
		assertEquals(items1, items);

	}

	@Test
	public void deleteItemsTest() {

		doNothing().when(itemRepository).delete(ITEMID);
		itemService.deleteItemById(ITEMID);
		verify(itemRepository, times(1)).delete(ITEMID);
	}

	@Test
	public void updateItemsTest() {
		
		when(itemRepository.save(item)).thenReturn(item);
		itemService.updateItem(item);
	}

	@Test
	public void saveItemsTest() {

		when(itemRepository.save(item)).thenReturn(item);
		itemService.saveItem(item);
	}
	
	@Test
	public void isItemsExistTest() {

		when(itemRepository.findOne(ITEMID)).thenReturn(item);
		boolean a=itemService.isItemExist(ITEMID);
		assertNotNull(a);
	}
	
	@Test
	public void isItemsExist1Test() {

		when(itemRepository.findOne(ITEMID)).thenReturn(null);
		boolean a=itemService.isItemExist(ITEMID);
		assertNotNull(a);
	}
	
	
	@Test
	public void itemModificationTest() {
		when(itemRepository.save(item)).thenReturn(item);
		itemService.itemModification(item);
	}

}
