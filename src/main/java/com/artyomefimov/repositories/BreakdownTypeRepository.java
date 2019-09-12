package com.artyomefimov.repositories;

import com.artyomefimov.database.dao.BreakdownTypeDao;
import com.artyomefimov.database.model.BreakdownType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BreakdownTypeRepository {
    private final BreakdownTypeDao breakdownTypeDao;

    @Autowired
    public BreakdownTypeRepository(BreakdownTypeDao breakdownTypeDao) {
        this.breakdownTypeDao = breakdownTypeDao;
    }

    public List<BreakdownType> getAll() {
        return breakdownTypeDao.getAll();
    }
}
