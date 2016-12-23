package com.oms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oms.pojo.CustomerOrder;

public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {

}
