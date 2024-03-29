package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Car;
import com.artyomefimov.database.repository.CarRepository;
import com.artyomefimov.web.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CarController {
    private CarRepository carRepository;
    private CarService carService;

    @Autowired
    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @GetMapping(value = "**/customer/{customerId}/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> getCarsByCustomerPassportNum(@PathVariable Long customerId) {
        return new ResponseEntity<>(
                carRepository.findAllByCustomer_CustomerId(customerId),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/master/{masterId}/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> getCarsByMasterPassportNum(@PathVariable Long masterId) {
        return new ResponseEntity<>(
                carRepository.findAllByMaster_MasterId(masterId),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "**/cars/car",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> createCar(@RequestBody String carJson) throws Exception {
        Car car = carService.resolveCarFromJson(carJson);
        return new ResponseEntity<>(
                carRepository.save(car),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "**/cars/car/{carId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> updateCar(@PathVariable Long carId,
                                         @RequestBody String carJson) throws Exception {
        Car car = carService.resolveCarFromJson(carJson);
        return new ResponseEntity<>(
                carRepository.save(car),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/cars/car/{carId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> getCar(@PathVariable Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        return car
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "**/cars/car/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        try {
            carRepository.deleteById(carId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
