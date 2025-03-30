package com.business.product.IntegrationTesting;

import com.business.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }


    @Test
    void testCreateAndGetProduct() throws Exception{

        //Create a product via POST Request
        mockMvc.perform(MockMvcRequestBuilders.post("/Product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Caramel Bread\",\"price\":5.00}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Caramel Bread"));

        //Retrieve the product via GET Request
        mockMvc.perform(MockMvcRequestBuilders.get("/Product"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Caramel Bread"));
    }
}
