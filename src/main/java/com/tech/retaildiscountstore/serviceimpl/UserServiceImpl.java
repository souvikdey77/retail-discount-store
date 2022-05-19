package com.tech.retaildiscountstore.serviceimpl;

import com.tech.retaildiscountstore.model.UserTypeEntity;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.repository.UserRepository;
import com.tech.retaildiscountstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author souvikdey
 * Business implementation for the user creation
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private SequenceGeneratorServiceImpl sequenceGeneratorService;

    /**
     * Method to create the user
     * @param userTO
     * @return UserTypeEntity
     */
    @Override
    public UserTypeEntity createUser(UserTO userTO) {
        UserTypeEntity userTypeEntity = new UserTypeEntity();
        UserTypeEntity existingUser = repository.findByUserName(userTO.getUserName());
        if(existingUser == null){
            userTypeEntity.set_id(sequenceGeneratorService.getSequenceNumber(UserTypeEntity.SEQUENCE_NAME));
            userTypeEntity.setUserType(userTO.getUserType());
            userTypeEntity.setUserName(userTO.getUserName());
            userTypeEntity.setNumberOfYears(userTO.getNumberOfYears());
            return repository.save(userTypeEntity);
        }
        return null;
    }
}
