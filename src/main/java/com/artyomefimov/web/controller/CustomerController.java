package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Customer;
import com.artyomefimov.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "**/workshop/{workshopId}/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getCustomersByWorkshopInn(@PathVariable Long workshopId) {
        return new ResponseEntity<>(
                customerRepository.findAllByWorkshop_WorkshopId(workshopId),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "**/customers/customer",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(
                customerRepository.save(customer),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "**/customers/customer/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
                                                   @RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(
                customerRepository.save(customer),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/customers/customer/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "**/customers/customer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        try {
            customerRepository.deleteById(customerId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
