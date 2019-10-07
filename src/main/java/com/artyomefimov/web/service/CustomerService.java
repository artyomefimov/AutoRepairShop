package com.artyomefimov.web.service;

import com.artyomefimov.Utils;
import com.artyomefimov.database.model.Customer;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final String WORKSHOP_ID = "workshopId";

    private WorkshopRepository workshopRepository;
    private ObjectMapper objectMapper;

    public CustomerService(WorkshopRepository workshopRepository, ObjectMapper objectMapper) {
        this.workshopRepository = workshopRepository;
        this.objectMapper = objectMapper;
    }

    public Customer resolveCustomerFromJson(String customerJson) throws Exception {
        Customer customer = objectMapper.readValue(customerJson, Customer.class);
        Long workshopId = Utils.resolveJsonNodeValue(objectMapper, customerJson, WORKSHOP_ID);
        if (workshopId != null)
            workshopRepository.findById(workshopId).ifPresent(customer::setWorkshop);
        return customer;
    }
}
