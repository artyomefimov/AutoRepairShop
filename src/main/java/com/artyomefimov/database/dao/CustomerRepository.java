package com.artyomefimov.database.dao;

import com.artyomefimov.database.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { }
