package com.tech.retaildiscountstore.service;

import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.model.UserModel;
import com.tech.retaildiscountstore.pojo.AdminTO;
import com.tech.retaildiscountstore.pojo.UserTO;

/**
 * @author souvikdey
 * UserService interface contract
 */
public interface UserService {

    AdminModel createAdmin(AdminTO adminTO);
    UserModel createUser(UserTO userTO);
}
