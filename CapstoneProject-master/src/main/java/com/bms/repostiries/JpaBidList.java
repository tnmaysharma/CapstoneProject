package com.bms.repostiries;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bms.domain.BidList;

public interface JpaBidList extends MongoRepository<BidList, String> {

}
