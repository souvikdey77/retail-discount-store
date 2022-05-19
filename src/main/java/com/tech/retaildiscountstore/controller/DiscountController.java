package com.tech.retaildiscountstore.controller;

import com.tech.retaildiscountstore.pojo.OrderDetailsTO;
import com.tech.retaildiscountstore.service.DiscountService;
import com.tech.retaildiscountstore.serviceimpl.DiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author souvikdey
 * This is the controller class for the discount functionality
 */
@RestController
@RequestMapping("/discount")
public class DiscountController {

    private final DiscountServiceImpl discountServiceImpl;

    @Autowired
    public DiscountController(DiscountServiceImpl discountServiceImpl){
        this.discountServiceImpl = discountServiceImpl;
    }

    /**
     * Method to generate the bill after providing the discount to the user
     *
     * @param orderDetailsTO
     * @return the final bill amount
     * @throws Exception
     */
    @PostMapping(value = "/user")
    public ResponseEntity<Double> getBillForUser(@RequestBody OrderDetailsTO orderDetailsTO) throws Exception {
        Double billAmount = discountServiceImpl.discountForUser(orderDetailsTO.getUserName(),orderDetailsTO.getOrderPrice());
        return new ResponseEntity<>(billAmount, HttpStatus.OK);
    }

}
