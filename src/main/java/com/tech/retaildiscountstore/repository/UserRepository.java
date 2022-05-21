package com.tech.retaildiscountstore.repository;

import com.tech.retaildiscountstore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author souvikdey
 * This is the UserRepository which is responsible to connect with Mongo db and create UserTypeEntity collection
 */
@Repository
public interface UserRepository extends MongoRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
