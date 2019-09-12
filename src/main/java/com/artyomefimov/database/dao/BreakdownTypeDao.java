package com.artyomefimov.database.dao;

import com.artyomefimov.database.model.BreakdownType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public class BreakdownTypeDao {
    private SessionFactory sessionFactory;

    @Autowired
    public BreakdownTypeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<BreakdownType> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BreakdownType> query = criteriaBuilder.createQuery(BreakdownType.class);
            query.from(BreakdownType.class);
            return session.createQuery(query).getResultList();
        }
    }
}
