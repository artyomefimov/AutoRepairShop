package com.artyomefimov.database.repository;

import com.artyomefimov.database.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterRepository extends JpaRepository<Master, Long> {
    List<Master> findAllByWorkshop_WorkshopId(Long inn);
    List<Master> findAllByLevel_LevelId(Long id);
}
