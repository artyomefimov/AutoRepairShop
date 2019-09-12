package com.artyomefimov.database.dao;

import com.artyomefimov.Constants;
import com.artyomefimov.database.model.BreakdownType;
import com.artyomefimov.database.model.builders.Builder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(Constants.BREAKDOWN_TYPE_DAO)
public class BreakdownTypeDao extends AbstractDao<BreakdownType> {
    @Autowired
    public BreakdownTypeDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public BreakdownType createObject(Builder<BreakdownType> builder) {
        return builder.build();
    }
}
