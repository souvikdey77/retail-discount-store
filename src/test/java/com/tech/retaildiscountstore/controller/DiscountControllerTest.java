package com.tech.retaildiscountstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.retaildiscountstore.pojo.OrderDetailsTO;
import com.tech.retaildiscountstore.service.UserSecurityService;
import com.tech.retaildiscountstore.serviceimpl.DiscountServiceImpl;
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

/**
 * @author souvikdey
 * DiscountController test class
 */
@WebMvcTest(DiscountController.class)
class DiscountControllerTest {

    @MockBean
    private DiscountServiceImpl discountServiceImpl;

    @MockBean
    private UserSecurityService userSecurityService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "roby", authorities = {"ADMIN"})
    void testDiscount() throws Exception {
        OrderDetailsTO orderDetailsTO = OrderDetailsTO.builder()
                        .orderPrice(3000d).userName("rob23").build();
        User user = new User("Joe","Joe", Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
        Mockito.when(userSecurityService.loadUserByUsername(Mockito.anyString())).thenReturn(user);
        Mockito.when(discountServiceImpl.discountForUser(Mockito.anyString(),Mockito.anyDouble())).thenReturn(1000d);
        String content = objectMapper.writeValueAsString(orderDetailsTO);
        MockHttpServletRequestBuilder mockRequest = post("/api/v1/user/discount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }


}
