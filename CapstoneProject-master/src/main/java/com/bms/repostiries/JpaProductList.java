package com.bms.repostiries;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bms.domain.ProductList;

public interface JpaProductList extends MongoRepository<ProductList, String>  {

}
