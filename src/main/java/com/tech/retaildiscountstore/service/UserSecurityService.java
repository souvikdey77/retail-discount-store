package com.tech.retaildiscountstore.service;

import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author souvikdey
 * This class is responsible to fetch the admin user detail from mongo and validate it
 *
 */
@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * This method will load check if the admin user is available and validate it
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminModel adminModel = adminRepository.findByUsername(username);
        if(adminModel == null){
            throw new UsernameNotFoundException("User is not available");
        }
        return new User(adminModel.getUsername(),adminModel.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
    }
}
