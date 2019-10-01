package com.artyomefimov.web.controller;

import com.artyomefimov.Utils;
import com.artyomefimov.database.model.Customer;
import com.artyomefimov.database.repository.CustomerRepository;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController {
    private CustomerRepository customerRepository;
    private WorkshopRepository workshopRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, WorkshopRepository workshopRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.workshopRepository = workshopRepository;
        this.objectMapper = objectMapper;
    }


    @GetMapping(value = "**/workshop/{workshopId}/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getCustomersByWorkshopId(@PathVariable Long workshopId) {
        return new ResponseEntity<>(
                customerRepository.findAllByWorkshop_WorkshopId(workshopId),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "**/customers/customer",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody String customerJson) throws Exception {
        Customer customer = resolveCustomerFromJson(customerJson);
        return new ResponseEntity<>(
                customerRepository.save(customer),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "**/customers/customer/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
                                                   @RequestBody String customerJson) throws Exception {
        Customer customer = resolveCustomerFromJson(customerJson);
        return new ResponseEntity<>(
                customerRepository.save(customer),
                HttpStatus.OK);
    }

    private Customer resolveCustomerFromJson(String customerJson) throws Exception {
        Customer customer = objectMapper.readValue(customerJson, Customer.class);
        Long workshopId = Utils.resolveObjectById(objectMapper, customerJson, "workshopId");
        if (workshopId != null)
            workshopRepository.findById(workshopId).ifPresent(customer::setWorkshop);
        return customer;
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
