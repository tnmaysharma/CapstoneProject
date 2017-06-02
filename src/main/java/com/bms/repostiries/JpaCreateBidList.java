package com.bms.repostiries;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bms.domain.CreateBidList;

public interface JpaCreateBidList extends MongoRepository<CreateBidList, String> {

}
