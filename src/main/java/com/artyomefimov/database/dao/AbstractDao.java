package com.artyomefimov.database.dao;

import com.artyomefimov.database.DatabaseException;
import com.artyomefimov.database.model.builders.Builder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractDao<T> {
    protected SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public abstract T createObject(Builder<T> builder);

    public Long create(T obj) throws DatabaseException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Long id = (Long) session.save(obj);
            session.getTransaction().commit();
            return id;
        }
    }

    public void update(T obj) throws DatabaseException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        }
    }

    public void delete(T obj) throws DatabaseException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        }
    }

    public T getById(Long id, Class<T> entityClass) throws DatabaseException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }
    }

    public List<T> getAll(Class<T> entityClass) throws DatabaseException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            query.from(entityClass);
            return session.createQuery(query).getResultList();
        }
    }
}
