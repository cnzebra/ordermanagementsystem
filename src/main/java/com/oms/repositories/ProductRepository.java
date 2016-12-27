package com.oms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.oms.pojo.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
