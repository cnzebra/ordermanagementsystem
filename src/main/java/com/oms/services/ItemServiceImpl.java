package com.oms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.pojo.Item;
import com.oms.repositories.ItemRepository;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item findItemById(Integer itemId) {
		return itemRepository.findOne(itemId);
	}

	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Override
	public void updateItem(Item item) {
		itemRepository.save(item);
	}

	@Override
	public void deleteItemById(Integer itemId) {
		itemRepository.delete(itemId);
	}

	@Override
	public List<Item> findAllItems() {
		List<Item> items = itemRepository.findAll();
		return items;
	}

	@Override
	public boolean isItemExist(Integer itemId) {
		return findItemById(itemId) != null;
	}
}
