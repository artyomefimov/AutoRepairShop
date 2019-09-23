package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Customer;
import com.artyomefimov.database.repository.CustomerRepository;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractTest {
    @MockBean
    private CustomerRepository customerRepository;

    private String jsonListCustomers;
    private String jsonSingleCustomer;

    public CustomerControllerTest() throws Exception {
        this.jsonListCustomers = objectMapper.writeValueAsString(customerList);
        this.jsonSingleCustomer = objectMapper.writeValueAsString(customerList.get(0));
    }

    @Test
    public void testGettingCustomersByWorkshopInn() throws Exception {
        when(customerRepository.findAllByWorkshop_WorkshopId(any(Long.class)))
                .thenReturn(customerList);

        mockMvc.perform(
                get("/workshop/3645123456/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListCustomers));
    }

    @Test
    public void testCreatingCustomer() throws Exception {
        doReturn(customerList.get(0)).when(customerRepository).save(any(Customer.class));

        mockMvc.perform(
                post("/customers/customer")
                        .content(jsonSingleCustomer)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleCustomer));
    }

    @Test
    public void testUpdatingCustomer() throws Exception {
        doReturn(customerList.get(0)).when(customerRepository).save(any(Customer.class));

        mockMvc.perform(
                put(
                        "/customers/customer/123")
                        .content(jsonSingleCustomer)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleCustomer));
    }

    @Test
    public void testGettingCustomerById() throws Exception {
        Optional<Customer> customerById = Optional.of(customerList.get(0));
        doReturn(customerById).when(customerRepository).findById(eq(Long.valueOf(123L)));

        mockMvc.perform(
                get("/customers/customer/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleCustomer));
    }

    @Test
    public void testDeletingCustomerById() throws Exception {
        mockMvc.perform(
                delete("/customers/customer/123"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}