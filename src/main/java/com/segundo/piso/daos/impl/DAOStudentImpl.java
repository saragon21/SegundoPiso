/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Alumno;
import com.segundo.piso.daos.DAOStudent;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SARAGON
 */
@Repository
public class DAOStudentImpl extends DAOBaseImpl<Alumno> implements DAOStudent {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Alumno getEventByCode(String code, boolean alumno) {
        try {
            return (Alumno) this.sessionFactory.getCurrentSession()
                    .createCriteria(Alumno.class)
                    .add(Restrictions.eq("codigo", code))
                    .add(Restrictions.eq("alumno", alumno))
                    .uniqueResult();
        } catch (NoResultException e) {
            //TODO - Logger
            return null;
        }
    }

    @Override
    @Transactional
    public List<Alumno> getStudentsByNameCode(String student, String code) {
        return this.sessionFactory.getCurrentSession().createCriteria(Alumno.class)
                .add(Restrictions.like("codigo", code, MatchMode.ANYWHERE))
                .add(Restrictions.like("nombre", student, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    @Transactional
    public List<Alumno> getStudentsByName(String student) {
        return this.sessionFactory.getCurrentSession().createCriteria(Alumno.class)
                .add(Restrictions.like("nombre", student, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    @Transactional
    public List<Alumno> getStudentsByCode(String code) {
        return this.sessionFactory.getCurrentSession().createCriteria(Alumno.class)
                .add(Restrictions.like("codigo", code, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    @Transactional
    public List<Alumno> getStudentsByStudent(boolean student) {
        return this.sessionFactory.getCurrentSession().createCriteria(Alumno.class)
                .add(Restrictions.eq("alumno", student))
                .addOrder(Order.desc("codigo"))
                .list();
    }

    @Override
    @Transactional
    public String getLastCode(boolean alumno) {
        String code = (String) this.sessionFactory.getCurrentSession().
                createCriteria(Alumno.class)
                .setProjection(Projections.max("codigo"))
                .add(Restrictions.eq("alumno", alumno))
                .uniqueResult();

        int newCode = Integer.valueOf(code);
        return String.valueOf(++newCode);
    }

    
}
