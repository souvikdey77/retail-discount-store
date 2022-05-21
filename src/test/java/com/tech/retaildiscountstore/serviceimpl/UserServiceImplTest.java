package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.model.UserModel;
import com.tech.retaildiscountstore.pojo.AdminTO;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.repository.AdminRepository;
import com.tech.retaildiscountstore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author souvikdey
 * Test cases for UserServiceImpl
 */
@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    @InjectMocks
    private UserServiceImpl serviceImpl;

    @Test
    void testCreateUser(){
        UserTO userTO = new UserTO();
        userTO.setUserName("rohit");
        userTO.setUserType("Customer");
        userTO.setNumberOfYears(2);

        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUsername("rohit");
        userTypeEntity.setNumberOfYears(2);

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encoded password");
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(repository.save(Mockito.any(UserModel.class))).thenReturn(userTypeEntity);
        UserModel result = serviceImpl.createUser(userTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals( "Customer",result.getUserType());
        Assertions.assertEquals("rohit",result.getUsername());
        Assertions.assertEquals( 2,result.getNumberOfYears());

    }

    @Test
    void testCreateAdmin(){
        AdminTO adminTO = new AdminTO();
        adminTO.setUsername("storeadmin");
        adminTO.setPassword("admin");

        AdminModel adminModel = new AdminModel();
        adminModel.setUsername("storeadmin");
        adminModel.setPassword("admin");

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encoded password");
        Mockito.when(adminRepository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(sequenceGeneratorService.getSequenceNumber(Mockito.anyString())).thenReturn(1);
        Mockito.when(adminRepository.save(Mockito.any(AdminModel.class))).thenReturn(adminModel);
        AdminModel result = serviceImpl.createAdmin(adminTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("storeadmin",result.getUsername() );
        Assertions.assertEquals("admin",result.getPassword());
    }

    @Test
    void testCreateUser_whenUserAlreadyAvailable(){
        UserTO userTO = new UserTO();
        userTO.setUserName("rohit");
        userTO.setUserType("Customer");
        userTO.setNumberOfYears(2);

        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Customer");
        userTypeEntity.setUsername("rohit");
        userTypeEntity.setNumberOfYears(2);

        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(userTypeEntity);
        UserModel result = serviceImpl.createUser(userTO);
        Assertions.assertNull(result);


    }

    @Test
    void testCreateAdmin_whenAdminAlreadyAvailable(){
        AdminTO adminTO = new AdminTO();
        adminTO.setUsername("storeadmin");
        adminTO.setPassword("admin");

        AdminModel adminModel = new AdminModel();
        adminModel.setUsername("storeadmin");
        adminModel.setPassword("admin");

        Mockito.when(adminRepository.findByUsername(Mockito.anyString())).thenReturn(adminModel);
        AdminModel result = serviceImpl.createAdmin(adminTO);
        Assertions.assertNull(result);
    }


}
