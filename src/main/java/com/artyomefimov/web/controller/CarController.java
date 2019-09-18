package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Car;
import com.artyomefimov.database.repository.CarRepository;
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
public class CarController {
    private CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "**/customers/{passportNum}/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> getCarsByCustomerPassportNum(@PathVariable Long passportNum) {
        return new ResponseEntity<>(
                carRepository.findAllByCustomer_CustomerPassportNum(passportNum),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/masters/{passportNum}/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> getCarsByMasterPassportNum(@PathVariable Long passportNum) {
        return new ResponseEntity<>(
                carRepository.findAllByMaster_MasterPassportNum(passportNum),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "/cars",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> createCar(@RequestBody @Valid Car car) {
        return new ResponseEntity<>(
                carRepository.save(car),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/cars/update/{carNumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> updateCar(@PathVariable String carNumber,
                                         @RequestBody @Valid Car car) {
        return new ResponseEntity<>(
                carRepository.save(car),
                HttpStatus.OK);
    }

    @GetMapping(value = "/cars/update/{carNumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> getCar(@PathVariable String carNumber) {
        Optional<Car> car = carRepository.findById(carNumber);
        return car
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/cars/{carNumber}")
    public ResponseEntity<Void> deleteCar(@PathVariable String carNumber) {
        try {
            carRepository.deleteById(carNumber);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
