package com.artyomefimov.database.repository;

import com.artyomefimov.database.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> { }
