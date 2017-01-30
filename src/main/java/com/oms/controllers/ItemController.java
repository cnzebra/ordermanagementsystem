package com.oms.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.oms.pojo.Item;
import com.oms.services.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	
	Logger log=Logger.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/itemList", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> listAllItems() {
		List<Item> items = itemService.findAllItems();
		if (items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Item getItem(@PathVariable("id") Integer id) {
		return itemService.findItemById(id);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createItem(@RequestBody Item item, UriComponentsBuilder ucBuilder) {
		if (itemService.isItemExist(item.getItemId())) {
			log.info("A Item with id " + item.getItemId() + " already exist");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		itemService.saveItem(item);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/item/{id}").buildAndExpand(item.getItemId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public void updateItem(@PathVariable("id") Integer id, @RequestBody Item item) {
		itemService.updateItem(item);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Item> deleteItem(@PathVariable("id") Integer id) {
		Item items = itemService.findItemById(id);
		if (items == null) {
			log.info("Unable to delete item with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		itemService.deleteItemById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/isExist/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Item> isItemExist(@PathVariable("id") Integer id) {
		Item item = itemService.findItemById(id);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(item, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/isModify/{id}/{quantity}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Item> itemsModifications(@PathVariable("id") Integer id,
			@PathVariable("quantity") Integer quantity) {
		Item item = itemService.findItemById(id);
		Integer invenQuantity = item.getQuantity();
		Integer modifiedQuantity = invenQuantity - quantity;
		item.setQuantity(modifiedQuantity);
		itemService.itemModification(item);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}