package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.model.UserModel;
import com.tech.retaildiscountstore.pojo.AdminTO;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.repository.AdminRepository;
import com.tech.retaildiscountstore.repository.UserRepository;
import com.tech.retaildiscountstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author souvikdey
 * Business implementation for the user creation
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method to create the admin
     * @param adminTO
     * @return AdminModel
     */
    @Override
    public AdminModel createAdmin(AdminTO adminTO) {
        AdminModel adminModel = new AdminModel();
        AdminModel existingUser = adminRepository.findByUsername(adminTO.getUsername());
        if(existingUser == null){
            adminModel.set_id(sequenceGeneratorService.getSequenceNumber(AdminModel.SEQUENCE_NAME));
            adminModel.setUsername(adminTO.getUsername());
            adminModel.setPassword(passwordEncoder.encode(adminTO.getPassword()));
            log.info("Admin is successfully created with username " + adminTO.getUsername());
            return adminRepository.save(adminModel);
        }
        log.info("Admin is already available!");
        return null;
    }

    /**
     * Method to create the user
     * @param userTO
     * @return UserModel
     */
    @Override
    public UserModel createUser(UserTO userTO) {
        UserModel userTypeEntity = new UserModel();
        UserModel existingUser = userRepository.findByUsername(userTO.getUserName());
        if(existingUser == null){
            userTypeEntity.set_id(sequenceGeneratorService.getSequenceNumber(UserModel.SEQUENCE_NAME));
            userTypeEntity.setUserType(userTO.getUserType());
            userTypeEntity.setUsername(userTO.getUserName());
            userTypeEntity.setPassword(passwordEncoder.encode(userTO.getPassword()));
            userTypeEntity.setNumberOfYears(userTO.getNumberOfYears());
            log.info("User is successfully created with username " + userTO.getUserName());
            return userRepository.save(userTypeEntity);
        }
        log.info("User is already available!");
        return null;
    }
}
