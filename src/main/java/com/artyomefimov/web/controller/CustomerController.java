package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Customer;
import com.artyomefimov.database.repository.CarRepository;
import com.artyomefimov.database.repository.CustomerRepository;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.artyomefimov.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.CUSTOMERS_PAGE)
public class CustomerController {
    private CustomerRepository customerRepository;
    private CarRepository carRepository;
    private WorkshopRepository workshopRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository,
                              CarRepository carRepository,
                              WorkshopRepository workshopRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.workshopRepository = workshopRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Customer createCustomer(@RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping(value = "/{passportNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Customer updateCustomer(@PathVariable Long passportNum,
                            @RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping(value = "/update/{passportNum}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long passportNum) {
        Optional<Customer> customer = customerRepository.findById(passportNum);
        return customer
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{passportNum}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long passportNum) {
        try {
            customerRepository.deleteById(passportNum);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/{passportNum}/cars")
    public String getCarsByCustomerPassportNum(@PathVariable Long passportNum,
                                               Model model) {
        model.addAttribute(
                WebConstants.CARS_ATTRIBUTE,
                carRepository.findAllByCustomer_CustomerPassportNum(passportNum));
        return WebConstants.CARS_PAGE;
    }

    @GetMapping(value = "/workshops")
    public String returnToWorkshopsPage(Model model) {
        model.addAttribute(
                WebConstants.WORKSHOPS_ATTRIBUTE,
                workshopRepository.findAll());
        return WebConstants.WORKSHOPS_PAGE;
    }
}
