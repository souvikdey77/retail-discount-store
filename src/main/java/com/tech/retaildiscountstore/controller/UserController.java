package com.tech.retaildiscountstore.controller;

import com.tech.retaildiscountstore.model.UserTypeEntity;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl serviceImpl;

    /**
     * Method to create the user
     * @param userTO
     * @return UserTypeEntity
     */
    @PostMapping("/create")
    public ResponseEntity<UserTypeEntity> createUser(@RequestBody UserTO userTO){
        UserTypeEntity userInfo =  serviceImpl.createUser(userTO);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }
}
