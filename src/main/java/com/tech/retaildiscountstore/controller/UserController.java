package com.tech.retaildiscountstore.controller;

import com.tech.retaildiscountstore.exception.UserAlreadyCreatedException;
import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.model.UserModel;
import com.tech.retaildiscountstore.pojo.AdminTO;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author souvikdey
 * This is the controller class responsible for creating the user
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl serviceImpl;

    /**
     * Method to create the admin user
     * @param adminTO
     * @return UserTypeEntity
     */
    @PostMapping("/admin/create")
    public ResponseEntity<AdminModel> createAdmin(@RequestBody AdminTO adminTO){
        AdminModel adminInfo =  serviceImpl.createAdmin(adminTO);
        return new ResponseEntity<>(adminInfo, HttpStatus.CREATED);
    }

    /**
     * Method to create the admin user
     * @param userTO
     * @return UserTypeEntity
     */
    @PostMapping("/user/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserTO userTO){
        UserModel userInfo =  serviceImpl.createUser(userTO);
        if(userInfo == null){
            throw new UserAlreadyCreatedException("User already created with username "+userTO.getUserName());
        }
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }
}
