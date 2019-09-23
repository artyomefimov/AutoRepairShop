package com.artyomefimov.database.repository;

import com.artyomefimov.database.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByWorkshop_WorkshopId(Long workshopId);
}
