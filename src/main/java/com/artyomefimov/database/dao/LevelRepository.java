package com.artyomefimov.database.dao;

import com.artyomefimov.database.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> { }
