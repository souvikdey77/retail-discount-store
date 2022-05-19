package com.tech.retaildiscountstore.repository;

import com.tech.retaildiscountstore.model.DiscountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author souvikdey
 * This is the DiscountRepository which is responsible for connecting with Mongo database and create DiscountEntity collection
 */
@Repository
public interface DiscountRepository extends MongoRepository<DiscountEntity, Long> {
}
