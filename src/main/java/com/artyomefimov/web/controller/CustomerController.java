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

    @GetMapping(value = "**/workshop/{inn}/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getCustomersByWorkshopInn(@PathVariable Long inn) {
        return new ResponseEntity<>(
                customerRepository.findAllByWorkshop_Inn(inn),
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

    @PutMapping(value = "**/customers/customer/{passportNum}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long passportNum,
                                                   @RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(
                customerRepository.save(customer),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/customers/customer/{passportNum}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long passportNum) {
        Optional<Customer> customer = customerRepository.findById(passportNum);
        return customer
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "**/customers/customer/{passportNum}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long passportNum) {
        try {
            customerRepository.deleteById(passportNum);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
