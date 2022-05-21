package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.model.UserTypeEntity;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    @InjectMocks
    private UserServiceImpl serviceImpl;

    @Test
    public void testCreateUser(){
        UserTO userTO = new UserTO();
        userTO.setUserName("rohit");
        userTO.setUserType("Customer");
        userTO.setNumberOfYears(2);

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUserName("rohit");
        userTypeEntity.setNumberOfYears(2);

        Mockito.when(repository.findByUserName(Mockito.anyString())).thenReturn(null);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(repository.save(Mockito.any(UserTypeEntity.class))).thenReturn(userTypeEntity);
        UserTypeEntity result = serviceImpl.createUser(userTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUserType(), "Customer");
        Assertions.assertEquals(result.getUserName(), "rohit");
        Assertions.assertEquals(result.getNumberOfYears(), 2);

    }

    @Test
    public void testCreateUser_whenUserAlreadyAvailable(){
        UserTO userTO = new UserTO();
        userTO.setUserName("rohit");
        userTO.setUserType("Customer");
        userTO.setNumberOfYears(2);

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUserName("rohit");
        userTypeEntity.setNumberOfYears(2);

        Mockito.when(repository.findByUserName(Mockito.anyString())).thenReturn(userTypeEntity);
        UserTypeEntity result = serviceImpl.createUser(userTO);
        Assertions.assertNull(result);


    }


}
