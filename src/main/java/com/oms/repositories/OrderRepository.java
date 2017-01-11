package com.oms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oms.pojo.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {

}
