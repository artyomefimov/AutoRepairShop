package com.artyomefimov.database.repository;

import com.artyomefimov.database.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMaster_MasterId(Long masterId);
    List<Car> findAllByCustomer_CustomerId(Long customerId);
}
