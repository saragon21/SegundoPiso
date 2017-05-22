/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.daos.DAOBase;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SARAGON
 * @param <E>
 */
@Repository
public class DAOBaseImpl<E> implements DAOBase<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public E save(E object) {
        try {
            this.sessionFactory.getCurrentSession().save(object);

            return object;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public E update(E object) {
        try {
            this.sessionFactory.getCurrentSession().update(object);

            return object;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public E delete(E object) {
        try {
            this.sessionFactory.getCurrentSession().delete(object);

            return object;
        } catch (HibernateException e) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public E merge(E object) {
        try {
            this.sessionFactory.getCurrentSession().merge(object);

            return object;
        } catch (HibernateException e) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public List<E> getAllRecords(Class clazz, Order ... orderBy) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(clazz);
        
        for (Order order : orderBy) {
            criteria.addOrder(order);
        }
        
        return criteria.list();
    }
    
    @Override
    @Transactional
    public E getRecordById(int id, Class clazz) {
        try {
        return (E) this.sessionFactory.getCurrentSession()
                .createCriteria(clazz)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        } catch (NoResultException e) {
            //TODO Logger
            return null;
        }
    }
    
    @Override
    @Transactional
    public List<E> getAllRecords(Class clazz, boolean status, Order ... orderBy) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(clazz)
                .add(Restrictions.eq("status", status));
        
        for (Order order : orderBy) {
            criteria.addOrder(order);
        }
        
        return criteria.list();
    }
    
    @Override
    @Transactional
    public List<E> getAllRecords(Class clazz, Criterion ... restrictions) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(clazz);
        
        for (Criterion restriction : restrictions) {
            criteria.add(restriction);
        }
        
        return criteria.list();
    }
}
