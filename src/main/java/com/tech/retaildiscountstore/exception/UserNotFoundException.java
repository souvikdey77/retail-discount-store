package com.tech.retaildiscountstore.exception;

/**
 * @author souvikdey
 * This is the custom exception : UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName){
        super(userName);
    }
}
