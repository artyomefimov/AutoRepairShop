package com.artyomefimov.database.dao;

import com.artyomefimov.Constants;
import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.model.builders.Builder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(Constants.WORKSHOP_DAO)
public class WorkshopDao extends AbstractDao<Workshop> {
    @Autowired
    public WorkshopDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Workshop createObject(Builder<Workshop> builder) {
        return builder.build();
    }
}
