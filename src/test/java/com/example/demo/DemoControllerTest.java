package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DemoController demoController;

    @Test
    public void aCustomerIsReturnedIfItExists() {

        Integer customerId = 5;

        when(customerRepository.findById(anyInt()))
                .thenReturn(Optional.of(Customer.builder().id(customerId).build()));

        ResponseEntity<Customer> customer = demoController.getCustomer(customerId);

        assertEquals(customerId, customer.getBody().getId());
    }

}