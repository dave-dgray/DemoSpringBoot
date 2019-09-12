package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
        customerRepository.save(Customer.builder().id(5).build());
    }

    @Test
    public void theCustomerIsReturnedIfItExists() throws Exception {
        final URI url = URI.create("/customers/5");
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':5}"));
    }

    @Test
    public void aBadRequestResponseIsReturnedIfCustomerDoesntExist() throws Exception {
        final URI url = URI.create("/customers/6");
        mockMvc.perform(get(url))
                .andExpect(status().isBadRequest());
    }
}