package com.tech.retaildiscountstore.service;

/**
 * @author souvikdey
 * DiscountService interface contract
 */
public interface DiscountService {
     Double discountForUser(String userName, Double orderedPrice) throws Exception;
}
