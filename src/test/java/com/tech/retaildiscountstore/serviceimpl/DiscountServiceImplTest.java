package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.Exception.UserNotFoundException;
import com.tech.retaildiscountstore.model.DiscountEntity;
import com.tech.retaildiscountstore.model.UserTypeEntity;
import com.tech.retaildiscountstore.repository.DiscountRepository;
import com.tech.retaildiscountstore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author souvikdey
 * DiscountServiceImplTest test class
 */
@SpringBootTest
public class DiscountServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    @InjectMocks
    private DiscountServiceImpl serviceImpl;

    @Test
    public void testDiscountForEmployee() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc560");
        discountEntity.setUserType("Employee");
        discountEntity.setNumberOfYear(4);
        discountEntity.setProductType("Electronics");

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Employee");
        userTypeEntity.setUserName("abc560");
        userTypeEntity.setNumberOfYears(4);
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc560",3000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    public void testDiscountForAffiliate() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc560");
        discountEntity.setUserType("Affiliate");
        discountEntity.setNumberOfYear(4);
        discountEntity.setProductType("Electronics");

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Affiliate");
        userTypeEntity.setUserName("abc561");
        userTypeEntity.setNumberOfYears(4);
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc561",4000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    public void testDiscountForCustomer_NumberOfYearGreaterThanTwo() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc562");
        discountEntity.setUserType("Customer");
        discountEntity.setNumberOfYear(4);
        discountEntity.setProductType("Electronics");

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUserName("abc562");
        userTypeEntity.setNumberOfYears(4);
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc562",5000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    public void testDiscountForCustomer_NumberOfYearLesserThanTwo() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc564");
        discountEntity.setUserType("Customer");
        discountEntity.setNumberOfYear(1);
        discountEntity.setProductType("Electronics");

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUserName("abc564");
        userTypeEntity.setNumberOfYears(1);
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc564",5000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    public void testDiscountForCustomer_UserNotFoundException() throws Exception {
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(null);
        try{
            serviceImpl.discountForUser("abc564",5000d);
        }
        catch(UserNotFoundException ex){
        }
    }


}
