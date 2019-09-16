package com.artyomefimov.database.dao;

import com.artyomefimov.database.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> { }
