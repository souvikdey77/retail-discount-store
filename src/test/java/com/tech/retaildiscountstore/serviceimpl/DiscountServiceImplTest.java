package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.exception.UserNotFoundException;
import com.tech.retaildiscountstore.model.DiscountEntity;
import com.tech.retaildiscountstore.model.UserModel;
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
class DiscountServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    @InjectMocks
    private DiscountServiceImpl serviceImpl;

    @Test
    void testDiscountForEmployee() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc560");
        discountEntity.setUserType("Employee");
        discountEntity.setNumberOfYear(4);
        discountEntity.setProductType("Electronics");

        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Employee");
        userTypeEntity.setUsername("abc560");
        userTypeEntity.setNumberOfYears(4);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc560",3000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    void testDiscountForAffiliate() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc560");
        discountEntity.setUserType("Affiliate");
        discountEntity.setNumberOfYear(4);
        discountEntity.setProductType("Electronics");

        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Affiliate");
        userTypeEntity.setUsername("abc561");
        userTypeEntity.setNumberOfYears(4);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc561",4000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    void testDiscountForCustomer_NumberOfYearGreaterThanTwo() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc562");
        discountEntity.setUserType("Customer");
        discountEntity.setNumberOfYear(4);
        discountEntity.setProductType("Electronics");

        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUsername("abc562");
        userTypeEntity.setNumberOfYears(4);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc562",5000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    void testDiscountForCustomer_NumberOfYearLesserThanTwo() throws Exception {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setUserName("abc564");
        discountEntity.setUserType("Customer");
        discountEntity.setNumberOfYear(1);
        discountEntity.setProductType("Electronics");

        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUsername("abc564");
        userTypeEntity.setNumberOfYears(1);
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(userTypeEntity);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(discountRepository.save(Mockito.any(DiscountEntity.class))).thenReturn(discountEntity);
        Double billAmount = serviceImpl.discountForUser("abc564",5000d);
        Assertions.assertNotNull(billAmount);

    }

    @Test
    void testDiscountForCustomer_UserNotFoundException() throws Exception {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);
        try{
            Double billAmount = serviceImpl.discountForUser("abc564",5000d);
            Assertions.assertNull(billAmount);
        }
        catch(UserNotFoundException ex){
        }
    }


}
