package com.tech.retaildiscountstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.retaildiscountstore.pojo.OrderDetailsTO;
import com.tech.retaildiscountstore.serviceimpl.DiscountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author souvikdey
 * DiscountController test class
 */
@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {

    @MockBean
    private DiscountServiceImpl discountServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "roby", authorities = {"admin"})
    public void test() throws Exception {
        OrderDetailsTO orderDetailsTO = OrderDetailsTO.builder()
                        .orderPrice(3000d).userName("rob23").build();

        Mockito.when(discountServiceImpl.discountForUser(Mockito.anyString(),Mockito.anyDouble())).thenReturn(1000d);
        String content = objectMapper.writeValueAsString(orderDetailsTO);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/discount/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
