package com.oms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oms.pojo.Item;

public interface ItemRepository extends MongoRepository<Item, Integer> {

}
