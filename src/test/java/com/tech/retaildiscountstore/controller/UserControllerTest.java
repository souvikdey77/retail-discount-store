package com.tech.retaildiscountstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.retaildiscountstore.model.UserTypeEntity;
import com.tech.retaildiscountstore.pojo.UserTO;
import com.tech.retaildiscountstore.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl serviceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        UserTO userTO = UserTO.builder().userName("ramesh12")
                .userType("Employee").build();

        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setUserType("Employee");
        userTypeEntity.setUserName("ramesh12");
        String content = objectMapper.writeValueAsString(userTO);
        Mockito.when(serviceImpl.createUser(Mockito.any(UserTO.class))).thenReturn(userTypeEntity);
        MockHttpServletRequestBuilder mockRequest = post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("ramesh12"))
                .andExpect(jsonPath("$.userType").value("Employee"));
    }

}
