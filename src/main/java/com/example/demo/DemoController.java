package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
class DemoController {

    private CustomerRepository customerRepository;

    @Autowired
    DemoController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{code}")
    ResponseEntity<Customer> getCustomer(@PathVariable("code") Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()){
            throw new DataIntegrityViolationException("???");
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Customer Doesn't Exist")
    public void conflict() {
        // Nothing to do
    }

}