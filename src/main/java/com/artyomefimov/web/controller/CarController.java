package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Car;
import com.artyomefimov.database.repository.CarRepository;
import com.artyomefimov.database.repository.CustomerRepository;
import com.artyomefimov.database.repository.MasterRepository;
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
@RequestMapping(WebConstants.CARS_PAGE)
public class CarController {
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private MasterRepository masterRepository;

    @Autowired
    public CarController(CarRepository carRepository,
                         CustomerRepository customerRepository,
                         MasterRepository masterRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.masterRepository = masterRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Car createCar(@RequestBody @Valid Car car) {
        return carRepository.save(car);
    }

    @PutMapping(value = "/{carNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Car updateCar(@PathVariable String carNumber,
                  @RequestBody @Valid Car car) {
        return carRepository.save(car);
    }

    @GetMapping(value = "/update/{carNumber}")
    public ResponseEntity<Car> getCar(@PathVariable String carNumber) {
        Optional<Car> car = carRepository.findById(carNumber);
        return car
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{carNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable String carNumber) {
        try {
            carRepository.deleteById(carNumber);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/masters")
    public String returnToMastersPage(Model model) {
        model.addAttribute(
                WebConstants.MASTERS_ATTRIBUTE,
                masterRepository.findAll());
        return WebConstants.MASTERS_PAGE;
    }

    @GetMapping(value = "/customers")
    public String returnToCustomersPage(Model model) {
        model.addAttribute(
                WebConstants.CUSTOMERS_ATTRIBUTE,
                customerRepository.findAll());
        return WebConstants.CUSTOMERS_PAGE;
    }
}
