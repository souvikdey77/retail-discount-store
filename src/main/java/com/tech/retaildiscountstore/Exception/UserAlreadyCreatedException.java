package com.tech.retaildiscountstore.Exception;

/**
 * @author souvikdey
 * This is the custom exception : UserAlreadyCreatedException
 */
public class UserAlreadyCreatedException extends RuntimeException{
    public UserAlreadyCreatedException(String userName){
        super(userName);
    }
}
