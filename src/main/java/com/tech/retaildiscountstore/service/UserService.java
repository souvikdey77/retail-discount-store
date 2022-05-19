package com.tech.retaildiscountstore.service;

import com.tech.retaildiscountstore.model.UserTypeEntity;
import com.tech.retaildiscountstore.pojo.UserTO;

/**
 * @author souvikdey
 * UserService interface contract
 */
public interface UserService {

    UserTypeEntity createUser(UserTO userTO);
}
