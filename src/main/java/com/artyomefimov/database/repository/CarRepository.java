package com.artyomefimov.database.repository;

import com.artyomefimov.database.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findAllByMaster_MasterPassportNum(Long masterPassportNum);
    List<Car> findAllByCustomer_CustomerPassportNum(Long customerPassportNum);
}
