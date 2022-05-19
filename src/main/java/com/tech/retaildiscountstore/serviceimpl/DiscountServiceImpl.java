package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.Exception.UserNotFoundException;
import com.tech.retaildiscountstore.model.DiscountEntity;
import com.tech.retaildiscountstore.model.UserTypeEntity;
import com.tech.retaildiscountstore.repository.DiscountRepository;
import com.tech.retaildiscountstore.repository.UserRepository;
import com.tech.retaildiscountstore.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author souvikdey
 * ServiceImpl class responsible for the business implementation
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    /**
     * Method to calculate the discount for the ordered price
     *
     * @param userName
     * @param orderedPrice
     * @return payable amount
     * @throws Exception
     */
    @Override
    public Double discountForUser(String userName, Double orderedPrice) throws Exception {
        DiscountEntity discountEntity = new DiscountEntity();
        Double discountedPrice = 0d;
        UserTypeEntity userTypeEntity = userRepository.findByUserName(userName);
        if(userTypeEntity == null){
            throw new UserNotFoundException(userName);
        }
        discountEntity.setUserName(userTypeEntity.getUserName());
        discountEntity.setUserId(sequenceGeneratorService.getSequenceNumber(DiscountEntity.SEQUENCE_NAME));
        discountEntity.setActualPrice(orderedPrice);
        discountEntity.setNumberOfYear(userTypeEntity.getNumberOfYears());
        discountEntity.setUserType(userTypeEntity.getUserType());
            if(userTypeEntity.getUserType().equalsIgnoreCase("Employee")){
                discountedPrice = getPriceAfterDiscount(orderedPrice,30);
                if(discountedPrice >= 100){
                    discountedPrice = discountedPrice - Double.valueOf((discountedPrice.intValue() / 100) * 5);
                }
            }else if(userTypeEntity.getUserType().equalsIgnoreCase("Affiliate")){
                discountedPrice = getPriceAfterDiscount(orderedPrice,10);
                if(discountedPrice >= 100){
                    discountedPrice = discountedPrice - Double.valueOf((discountedPrice.intValue() / 100) * 5);
                }
            }else if(userTypeEntity.getUserType().equalsIgnoreCase("Customer")){
                if(userTypeEntity.getNumberOfYears() > 2){
                    discountedPrice = getPriceAfterDiscount(orderedPrice,5);
                    if(discountedPrice >= 100){
                        discountedPrice = discountedPrice - Double.valueOf((discountedPrice.intValue() / 100) * 5);
                    }
                }
            }
            discountEntity.setDiscountedPrice(discountedPrice);
            discountRepository.save(discountEntity);
        return discountedPrice;
    }

    /**
     * Method to calculate the price after applied percentage discount
     *
     * @param orderedPrice
     * @param percentage
     * @return
     */
    private Double getPriceAfterDiscount(Double orderedPrice, int percentage){
        return orderedPrice - (orderedPrice * (percentage /100));
    }
}
