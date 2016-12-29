package com.oms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oms.pojo.ConfirmOrder;

public interface ConfirmOrderRepository extends MongoRepository<ConfirmOrder, String> {

}
