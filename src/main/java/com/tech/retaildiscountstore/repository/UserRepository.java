package com.tech.retaildiscountstore.repository;

import com.tech.retaildiscountstore.model.UserTypeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author souvikdey
 * This is the UserRepository which is responsible to connect with Mongo db and create UserTypeEntity collection
 */
@Repository
public interface UserRepository extends MongoRepository<UserTypeEntity, Long> {
    UserTypeEntity findByUserName(String userName);
}
