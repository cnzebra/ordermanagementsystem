package com.oms.services;

import java.util.List;

import com.oms.pojo.Item;

public interface ItemService {

	Item findItemById(Integer itemId);

	void saveItem(Item item);

	void updateItem(Item item);

	void deleteItemById(Integer itemId);

	List<Item> findAllItems();

	public boolean isItemExist(Integer itemId);

}
