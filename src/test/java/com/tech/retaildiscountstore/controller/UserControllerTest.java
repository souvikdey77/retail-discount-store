package com.tech.retaildiscountstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.retaildiscountstore.model.AdminModel;
import com.tech.retaildiscountstore.model.UserModel;
import com.tech.retaildiscountstore.pojo.AdminTO;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.service.UserSecurityService;
import com.tech.retaildiscountstore.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl serviceImpl;

    @MockBean
    private UserSecurityService userSecurityService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "Joe", authorities = {"ADMIN"})
    void testCreateUser() throws Exception {
        UserTO userTO = UserTO.builder().userName("ramesh12")
                .userType("Employee").build();

        User user = new User("Joe","Joe", Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
        UserModel userTypeEntity = new UserModel();
        userTypeEntity.setUserType("Employee");
        userTypeEntity.setUsername("ramesh12");
        String content = objectMapper.writeValueAsString(userTO);
        Mockito.when(serviceImpl.createUser(Mockito.any(UserTO.class))).thenReturn(userTypeEntity);
        Mockito.when(userSecurityService.loadUserByUsername(Mockito.anyString())).thenReturn(user);
        MockHttpServletRequestBuilder mockRequest = post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }
    @Test
    void testAdminUser() throws Exception {
        AdminTO adminTO = AdminTO.builder().username("storeadmin")
                .password("admin").build();

        AdminModel adminModel = new AdminModel();
        adminModel.setUsername("storeadmin");
        adminModel.setPassword("admin");
        String content = objectMapper.writeValueAsString(adminTO);
        Mockito.when(serviceImpl.createAdmin(Mockito.any(AdminTO.class))).thenReturn(adminModel);
        MockHttpServletRequestBuilder mockRequest = post("/api/v1/admin/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }
}
