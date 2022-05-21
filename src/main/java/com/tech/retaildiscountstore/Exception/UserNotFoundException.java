package com.tech.retaildiscountstore.Exception;

/**
 * @author souvikdey
 * This is the custom exception : UserNotFoundException
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName){
        super(userName);
    }
}
