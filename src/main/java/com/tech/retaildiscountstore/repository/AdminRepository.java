package com.tech.retaildiscountstore.repository;

import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author souvikdey
 * This is the AdminRepository which is responsible to connect with Mongo db and create admin user
 */

@Repository
public interface AdminRepository extends MongoRepository<AdminModel,Integer> {
    AdminModel findByUsername(String username);
}
