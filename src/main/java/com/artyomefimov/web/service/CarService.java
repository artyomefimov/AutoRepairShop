package com.artyomefimov.web.service;

import com.artyomefimov.Utils;
import com.artyomefimov.database.model.Car;
import com.artyomefimov.database.repository.CustomerRepository;
import com.artyomefimov.database.repository.MasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private static final String MASTER_ID = "masterId";
    private static final String CUSTOMER_ID = "customerId";

    private ObjectMapper objectMapper;
    private MasterRepository masterRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CarService(ObjectMapper objectMapper, MasterRepository masterRepository, CustomerRepository customerRepository) {
        this.objectMapper = objectMapper;
        this.masterRepository = masterRepository;
        this.customerRepository = customerRepository;
    }

    public Car resolveCarFromJson(String carJson) throws Exception {
        Car car = objectMapper.readValue(carJson, Car.class);
        Long masterId = Utils.resolveJsonNodeValue(objectMapper, carJson, MASTER_ID);
        if (masterId != null)
            masterRepository.findById(masterId).ifPresent(car::setMaster);
        Long customerId = Utils.resolveJsonNodeValue(objectMapper, carJson, CUSTOMER_ID);
        if (customerId != null)
            customerRepository.findById(customerId).ifPresent(car::setCustomer);
        return car;
    }
}
